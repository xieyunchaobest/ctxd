package com.cattsoft.pub.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 将sql语句中的分区表加上partition显示指定。
 * 
 * @author mjh E-mail:mjh@cattsoft.com
 * @version 1.0 创建时间：2007-8-13 下午05:24:58 类说明
 */
public final class PartitionUtil {
    private static Logger log = Logger.getLogger(PartitionUtil.class);

    // 分区表名称列表 List<String>
    private static List ptTableNames;

    // 分区名称列表 Map<String, String>
    private static Map ptNames;

    private static final String defaultPartition = "p000";

    static {
        ptTableNames = new ArrayList();
        ptNames = new HashMap();

        SAXReader saxReader = new SAXReader();
        String file = PartitionUtil.class.getClassLoader().getResource("partition_config.xml")
                .getFile();
        Document document;
        try {
            document = saxReader.read(file);
            List tables = document.selectNodes("//table/name");
            if (tables != null) {
                log.debug("Total partition tables: " + tables.size());
            }
            Iterator tit = tables.iterator();
            while (tit.hasNext()) {
                Node node = (Node) tit.next();
                log.debug(node.getText());
                ptTableNames.add(node.getText().toUpperCase());
            }
            List partitions = document.selectNodes("//partition");
            if (partitions != null) {
                log.debug("Total partitions: " + partitions.size());
            }
            Iterator pit = partitions.iterator();
            while (pit.hasNext()) {
                Node partition = (Node) pit.next();
                Node localnet = partition.selectSingleNode("partition-key");
                Node name = partition.selectSingleNode("partition-name");
                log.debug("partitionKey: " + localnet.getText() + " partitionName: "
                        + name.getText());
                ptNames.put(localnet.getText(), name.getText());
            }
        } catch (DocumentException e) {
            log.error("读取并解析XML过程出错...");
        }
    }

    
    /**
     * 将sql语句中的分区表加上partition显示指定。 限制：1-在写sql的时候，表名前后一定要有空格，即使前面有逗号或者结尾有括号。2-字段名尽量不要与表名相同，如果相同尽量用
     * A.字段名 方式来使用。
     * 
     * @param fromSql
     * @param localNetId
     * @return
     */
    public static String getPartitionSQL(String sql, String localNetId) {
        // 示例原sql
        // select count(*) from serv a,serv_acc_nbr b
        // where a.serv_id=b.serv_id
        // and a.sts='A'
        // and b.sts='A'
        // 示例目标sql
        // select count(*) from serv partition(partition_3) a,serv_acc_nbr
        // partition(partition_3) b
        // where a.serv_id=b.serv_id
        // and a.sts='A'
        // and b.sts='A'

        // 如果未指定localNetId, 则不指定分区（即使是分区表）
        if (localNetId == null || "".equals(localNetId.trim())) {
            return sql;
        }

        String[] splitFrom = sql.split(" ");
        StringBuffer ret = new StringBuffer();
        List fldLst = new ArrayList();
        // printTimestamp();
        for (int i = 0; i < splitFrom.length; i++) { // 此循环把sql每个段加入到lst中
            // log.debug(splitFrom[i]);
            if (splitFrom[i].indexOf(",") < 0 && splitFrom[i].indexOf(")") < 0) { // 如果分出来的串中没有逗号和分号，则可以加到list中
                fldLst.add(splitFrom[i]);
            } else {
                addFld(fldLst, splitFrom[i]); // 否则进一步切分
            }
        }
        String fldName = null;
        String nextFld = null;
        for (int i = 0; i < fldLst.size(); i++) { // 此循环把lst中的段拼凑成sql，包含分区名
            fldName = (String) fldLst.get(i);
            if (ptTableNames.contains(fldName.toUpperCase())) {
                ret.append(fldName).append(" partition(").append(getOneTable(fldName, localNetId))
                        .append(") ");
            } else {
                boolean isKeyWord = false;
                if (fldName.indexOf("count") >= 0 || fldName.indexOf("sum") >= 0) { // 判断前面是否是关键词，比如count
                    isKeyWord = true;
                    nextFld = (String) fldLst.get(i + 1);
                }
                if (isKeyWord && nextFld.equalsIgnoreCase(")")) {
                    ret.append(fldName);
                } else {
                    ret.append(fldName).append(" ");
                }
            }
        }
        // printTimestamp();

        return ret.toString();
    }

    /*
     * 就包含逗号和分号的字符串进一步拆分，并加入到list中。
     */
    private static void addFld(List fldLst, String split1) {
        if (split1.length() == 1) { // 仅有一个字符则直接加入到list，并返回
            fldLst.add(split1);
            return;
        }
        if (split1.indexOf(",") == 0) { // 逗号为头一个字符
            fldLst.add(",");
            split1 = split1.substring(2);
            // log.debug(split1);
        }
        String end = null;

        if (split1.indexOf(")") == split1.length() - 1) { // 如果最后一个字符为括号
            split1 = split1.substring(0, split1.length() - 1);
            // log.debug(split1);
            end = ")";
        } else if (split1.indexOf(",") == split1.length() - 1) { // 如果最后一个字符为逗号
            split1 = split1.substring(0, split1.length() - 1);
            // log.debug(split1);
            end = ",";
        }
        if (split1.indexOf(",") > 0) { // 仅包括逗号，比如from bbb,xxx,ddd后面的字符串
            String[] dou = split1.split(",");
            for (int i = 0; i < dou.length; i++) {
                fldLst.add(dou[i]);
                if (i < dou.length - 1) {
                    fldLst.add(",");
                }
            }
        } else {
            fldLst.add(split1);
        }
        if (end != null) {
            fldLst.add(end);
        }
    }

    private static String getOneTable(String splitOne, String localNetId) {
        String partition = null;
        if (ptNames.containsKey(localNetId)) {
            partition = (String) ptNames.get(localNetId);
        } else { // default
            partition = defaultPartition;
        }
        return partition;
    }

    private static void printTimestamp() {
        if (log.isDebugEnabled()) {
            log.debug(new Date(System.currentTimeMillis()));
        }
    }

    public static void main(String[] args) {
        String fromSql = "select a.serv_id, b.bank_id from serv a, bank b where 1=1 and b.bank_id = ? and b.sts_date = to_date('2007-01-01', 'yyyy-MM-dd')";
        log.debug("start...................");
        printTimestamp();
        String partitionSql = PartitionUtil.getPartitionSQL(fromSql, "0");
        log.debug("sql after partitioning is: " + partitionSql);
        printTimestamp();
        log.debug("end....................");
    }

}
