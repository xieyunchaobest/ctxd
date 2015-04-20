package com.cattsoft.pub;

public class SysConstants {

	// 系统参数

	public static final String SYSTEM_PRPTY_CONTEXT_CONFIG_URL = "contextURL";
	
	public static final String SYSTEM_PRPTY_DAO_CONFIG_URL = "daoconfigURL";

	// 系统用户行为日志操作类型
	public static final String LOG_ADD = "A"; // 增加操作

	public static final String LOG_MOD = "M"; // 修改操作

	public static final String LOG_DELETE = "D"; // 删除操作
	
	public static final String LOG_CANCEL = "C"; // 禁用操作

	//
	public static final String ACTIVITY = "ACTIVITY"; 
	public static final String YES = "1";

	public static final String NO = "0";

	public static final String TRUE = "Y";

	public static final String FALSE = "N";

	public static final String PARTY_ROLE_TYPE_ID = "7";// 员工角色类型

	// 空字符串
	public static final String NULL_STRING = "";

	// 在用和注销
	public static final String USE_YES_STS = "A";

	public static final String USE_NO_STS = "P";

	// 有无条件退单方式
	public static final String SO_RETURN_TYPE = "1";// 1:无条件 0：有条件

	// 竣工环节
	public static final String STEP_FOR_SO_FINISH = ""; // 

	public static final String ONE = "1";

	// 异常编码
	public static final String EXP_CODE = "EXP_CODE";

	public static final String EXP_MSG = "EXP_MSG";

	// 系统回单人
	public static final String SYS_RT_STAFF_ID = "999999999";
	//系统回单人名字
	public static final String SYS_RT_STAFF_NAME = "系统";

	// 系统回单人部门
	public static final String SYS_RT_DEPT_ID = "999999999";

	public static final String SYS_DEFAULT_USER_ID = "40009";

	// 查询条数限制
	public static final String SYS_ROW_LIMIT = "21009";

	// 缓存刷新地址

	public static final String DATA_CACHE_REFRESH_ADDR = "21012";

	/** ****************************************系统管理************************************* */
	// 公告管理
	public static final String BULLETIN_TYPE_B = "B";// 系统公告
	public static final String BULLETIN_TYPE_S = "S";// 促销公告
	
	// 附件
	public static final String ATTACHMENT_FOR_SCENE_B = "B";// 公告栏对应的附件
	

	/** ****************************************定单类************************************* */

	public static final String SO_COUNT_IN_CO = "1"; //co包含so总数
	
	public static final String REC_SO_COUNTS = "1";  //co已经接收到的so数
	
	public static final String COMPL_FLAG = "N"; 
	
	public static final String CO_NBR_SEQ = "CO_NBR";
	
	/** 订单快捷查询 开关配置id */
	public static final String SYS_CONFIG_CO_QUICK_QUERY = "71010";
	
	// ---------------SO_BOOK
	public static final String SO_BOOK_WO_STS_WAIT = "D";// 等待处理

	public static final String SO_BOOK_WO_STS_DOING = "P";// 正在处理

	public static final String SO_BOOK_WO_STS_COMPLETE = "C";// 处理完成

	public static final String SO_BOOK_METHOD_NAME_FOR_ADD = "addSoBook";

	public static final String SO_BOOK_METHOD_NAME_FOR_MOD = "updateSoBook";

	public static final String SO_BOOK_METHOD_NAME_FOR_CANCLE = "cancleBook";
	
	public static final String SO_BOOK_CRM = "34102";
	
	public static final String EXPT_TYPE_SO_BOOK = "5"; //处理类型 5 预约变更

	public static final String SO_BOOK_XML = "SO_BOOK_XML"; // 预约信息XML
	
	public static final String RET_STRING_ARR = "RET_STRING_ARR"; // 预约返回的数组信息
	//定单来源
	public static final String SO_RES_SYSTEM_SELF = "SELF";
	public static final String SO_RES_SYSTEM_CRM = "CRM";
	public static final String SO_RES_SYSTEM_RMS = "RMS";
	public static final String SO_RES_SYSTEM_IVR = "IVR";
	
	

	//MSG_QUEUE.MSG_TYPE
	public static final String MSG_TYPE_SEND = "MT";  //msg_queue.msg_type 系统发送的消息
	
	public static final String MSG_TYPE_RECE="MO";//msg_queue.msg_type 系统接收的消息
	
	public static final int MSG_QUEUE_HANDLE_COUNT=3;//msg_queue.msg_type 系统接收的消息

	public static final String MSG_QUEUE_HANDLE_Flag_W = "W";// 等待处理

	public static final String MSG_QUEUE_HANDLE_Flag_C = "C";// 处理完成

	public static final String MSG_QUEUE_HANDLE_Flag_F = "F";//处理失败

	//MSG_RECEIVE_QUEUE

	public static final String MSG_RECEIVE_QUEUE_STS_D = "D";// 等待处理

	public static final String MSG_RECEIVE_QUEUE_STS_C = "C";// 处理完成

	public static final String MSG_RECEIVE_QUEUE_STS_F = "F";//处理失败
	
	
	//短信回单指令
	public static final String SP_MSG_ORDER_CGHD="SPCGHD";//服务开通成功回单
	public static final String MSG_ORDER_CGHD="CGHD";//成功回单
	
	public static final String MSG_ORDER_SBHD="SBHD";//失败回单
	
	public static final String MSG_ORDER_CP="CP";//工单重派
	
	public static final String MSG_ORDER_JDFK="JDFK";//进度反馈
	
	public static final String MSG_ORDER_ZP="ZP";//转派
	
	public static final String MSG_ORDER_JP="JP";//加派
	
	public static final String MSG_ORDER_LD = "LD"; // 领单
	
	
	// 定单STS
	public static final String SO_CHANGE_GROUP_STS = "B";// 等待撤改

	// 定单状态
	public static final String SO_WAIT_UPD_CANCEL = "K";// 等待撤改

	public static final String SO_SUSPEND = "s";// 缓装

	public static final String SO_GIVEWAY = "h";// 撤单处理中

	public static final String SO_MODIFY = "g";// 改单处理中

	public static final String DEAL_FLAG_REVERSE = "1";// 反向单

	public static final String DEAL_FLAG_DIRECT = "0";// 正向单

	public static final String SO_BIDE = "Y";// 待装

	public static final String SO_PROCESSING = "P";// 正常处理中

	public static final String SO_EXPLORATION = "V";// 外堪

	public static final String SO_RETURN_FAIL = "F";// 退单处理中

	public static final String SO_BE_ABOUT_TO = "A";// 就绪

	public static final String SO_WAIT_CHOICE_PROC = "W";// 等待选择流程

	public static final String SO_MATCH_FAIL = "M";// 模版匹配失败

	public static final String SO_WAIT_START = "D";// 等待启动流程

	public static final String SO_START_ERROR = "E";// 启动流程失败

	public static final String SO_FUTILITY = "R";// 失效

	public static final String SO_FINISH = "C";// 定单竣工

	public static final String SO_WORK_FINISH = "S";// 施工竣工

	public static final String SO_WAIT_SO_CANCEL = "B";// 等待原单撤销
	
	public static final String SO_AUDIT = "T";// 需要审核
	
	public static final String SO_STS_TEMP = "t";// 初始录入
	
	// 协同状态
	public static final String COLLAB_STS_PROC_WAIT = "W";// 流程协同等待

	public static final String COLLAB_STS_STEP_WAIT = "S";// 环节协同等待

	public static final String COLLAB_STS_NOMOR = "N";// 正常

	public static final String REAL_TIME_FLAG_YES = "Y";//
	
	public static final String REAL_TIME_FLAG_JMS = "J";//

	public static final String REAL_TIME_FLAG_NO = "N";//


	// 定单类型
	public static final String SO_TYPE_FUTILITY = "1";// 注销单

	public static final String SO_TYPE_MODIFY = "2";// 修改单

	public static final String SO_TYPE_NORMAL = "0";// 正常单

	public static final String SO_TYPE_FOR_WAIT = "3";// 缓装单

	public static final String SO_TYPE_FOR_AGAIN = "4";// 开装单

	public static final String SO_TYPE_FOR_CHANGE_GROUP = "5";// 改单改群组

	// 工单回单方式
	public static final String WO_RET_SUCCESS = "0";// 正常回单

	public static final String WO_RET_FAIL = "1";// 失败回单
	
	public static final String WO_RET_CODE_FOR_PARALLEL_WO = "WO_RET_CODE_FOR_PARALLEL_WO"; //由于并行工单未竣工而不能回单
	
	public static final String WO_RET_SUCCESS_BOTH = "2";// 主单和从单都一起正常回单

	// 撤单的处理方式
	public static final int BIDE_TYPE = 1;// 待装处理方式

	// 定单的执行方式
	public static final String SO_AUTO_EXEC = "A";// 自动启动流程

	public static final String SO_UN_AUTO_EXEC = "M";// 人工选择流程

	// 定单组类型
	public static final String GROUP_FLAG_FOR_YES = "Y";// 组标志

	public static final String GROUP_FLAG_FOR_NO = "N";// 非组标志

	// 退单允许标志
	public static final String CANCEL_FLAG_FOR_NO = "1";// 不允许

	public static final String CANCEL_FLAG_FOR_YES = "0";// 允许

	// 调用方式
	public static final String CALL_FLAG_FOR_JAVA = "J";// JAVA调用

	public static final String CALL_FLAG_FOR_PROCDURE = "P";// 存储过程调用

	// INTER_CALL_CONFIG的调用时机
	public static final String INTER_CALL_CONFIG_CALL_POINT_BUILD = "D";

	public static final String INTER_CALL_CONFIG_CALL_POINT_FETCH = "P";

	public static final String INTER_CALL_CONFIG_CALL_POINT_RET = "C";

	public static final String INTER_CALL_CONFIG_CALL_POINT_UNABLE = "R";

	// 存储过程调用返回值
	public static final String CALL_PROCEDURE_RET_VALUE = "VALUE";// 返回值

	public static final String CALL_PROCEDURE_RET_STATUS = "RET_STATUS";// 返回状态

	public static final String CALL_PROCEDURE_RET_DESC = "RET_DESC";// 返回描述

	// 预约标志
	public static final String BOOK_FLAG_FOR_YES = "Y"; // 预约

	public static final String BOOK_FLAG_FOR_NO = "N"; // 非预约

	public static final String BOOK_TASK_NUM = "6"; // 饱和工作量

	// 定单锁定状态
	public static final String SO_LOCK_STS_NO = "N";// 未锁定

	public static final String SO_LOCK_STS_YES = "Y";// 锁定

	public static final String SO_LOCK_STS_CANCEL_SO = "H";// 撤单锁定

	public static final String SO_LOCK_STS_CHANGE_SO = "G";// 改单锁定

	public static final String SO_LOCK_STS_DELAY_SO = "S";// 缓装锁定
	
	public static final String SO_LOCK_STS_RELATE_SO = "R";// 保障关联锁定

	// 调用工作流的方式
	public static final String CALL_TYPE_FOR_START_PROCESS = "0";// 启动流程

	public static final String CALL_TYPE_FOR_FAIL_REASON = "1";// 启动流程

	// 消息处理标志 MSG_FLAG
	public static final String SO_MSG_FLAG_NOT_PROCESS = "0";// 0 尚未处理

	public static final String SO_MSG_FLAG_SUCCESS_PROCESS = "1";// 1 处理成功

	public static final String SO_MSG_FLAG_FAIL_PROCESS = "2";// 2 处理失败

	// SO_RES分配标志
	public static final String ASSIGN_FLAG_RES_ASSIGN = "Y";// Y 资源分配产生

	public static final String ASSIGN_FLAG_CRM_ASSIGN = "N";// N 上游携带数据

	// SO_RES自我标志
	public static final String SO_RES_IS_SELF_YES = "Y";// Y 是

	public static final String SO_RES_IS_SELF_NO = "N";// N 否，关联的

	// SO SO_
	public static final String SO_SEQ_DEFAULT = "0";

	// 收费状态 PAY_STS C1 Y
	public static final String SO_PAY_STS_YES = "Y";// Y 已收费

	public static final String SO_PAY_STS_NO = "N";// N 未收费

	// 是否已精确预约 ACCU_FLAG
	public static final String ACCU_FLAG_YES = "Y";// 已精确预约

	public static final String ACCU_FLAG_NO = "N";// 未精确预约

	// 催单标志 HASTEN_FLAG N Y 有，之前资源发过催单
	public static final String HASTEN_FLAG_YES = "Y";

	public static final String HASTEN_FLAG_NO = "N";

	// 统一工单接口返回结果
	public static final String RET_NAI_SUCCESS = "C";// 成功

	public static final String RET_NAI_FAIL = "F";// 失败

	public static final String RET_NAI_CONTINUE = "N";// 正常流转

	public static final String RET_NAI_TO_MANUAL = "M";// 需要转人工处理

	// 回单接口启动流程的参数实例
	public static final String WORK_FLOW_PARAMETER_PROD_ID = "PROD_ID";

	public static final String WORK_FLOW_PARAMETER_BUSI_ID = "BUSI_ID";

	public static final String WORK_FLOW_PARAMETER_EXP_ID = "EXP_ID";

	public static final String WORK_FLOW_PARAMETER_PRIORITY = "PRIORITY";

	public static final String WORK_FLOW_PARAMETER_LOCAL_NET = "LOCAL_NET_ID";

	// 触发方式
	public static final String METHOD_TRIGER_YES = "Y"; // 业务规则触发

	// 定单流程处理返回结果
	public static final String SO_PROC_RET_CODE = "PROC_RET_CODE"; // add at

	// 08.07.25
	// by
	// huangyx
	

	/** ************************工单类**************************************************************************** */
	// 工单业务状态
	
	public static final String BUSI_STS_DELAY = "H";// 缓装
	
	public static final String BUSI_STS_BIDE = "Y";// 待装

	public static final String BUSI_STS_NORMAL = "N";// 正常

	public static final String BUSI_STS_FAIL = "F";// 失败回单

	public static final String BUSI_STS_AUDIT = "A";// 等待审核

	public static final String BUSI_STS_STAY = "S";// 失败滞留

	public static final String BUSI_STS_REMOKE = "K";// 等待撤改

	public static final String BUSI_STS_FAIL_INVOKE = "I";// 调用失败

	// 施工方式
	public static final String WO_WORK_MODE_MANUAL = "M"; // 人工

	public static final String WO_WORK_MODE_AUTO = "A"; // 自动

	public static final String WO_WORK_MODE_AUTO_MANUAL = "AM"; // 人工自动

	// 施工处理定制列类型
	public static final String TAB_COLUMN_COL_TYPE_NUMBER = "N";// 数值型

	public static final String TAB_COLUMN_COL_TYPE_DATE = "D";// 日期型

	public static final String TAB_COLUMN_COL_TYPE_STRING = "S";// 字符串型

	// 工单过程状态
	public static final String RUN_STS_PROCESSING = "P";// 处理中

	public static final String RUN_STS_COMPLETE = "C";// 完成

	public static final String RUN_STS_WAIT_PROCESS = "D";// 等待处理

	public static final String RUN_STS_WAIT_RETURN = "W";// 等待处理

	public static final String RUN_STS_REVOKE = "R";// 已经作废

	public static final String RUN_STS_DISP_FAIL = "I";// 调度失败

	// 方向标志
	public static final String DIRECTION_FORWORD = "0";// 正向

	public static final String DIRECTION_INVERT = "1";// 反向

	// 异常原因
	public static final String FAIL_REASON_EXCEPTION_INVOKE_TO_CRM = "1040110";// 退单审核异常前调到CRM

	public static final String FAIL_REASON_TO_BE_INSTALLED = "20003001";// 待装转入无需确认原因

	public static final String FAIL_REASON_TO_BE_INSTALLED_CONFIRM = "20003002";// 待装转入需要确认原因
	
	public static final String SYS_CONFIG_IS_GET_DEFAULT_WORK_AREA = "46010";// 待装单是否派到默认工区

	public static final String FAIL_REASON_SPECIAL_OVERTIME_REASON = "20002001";// 超期特殊原因
	
	public static final String FAIL_REASON_DELAY_FIX_ID = "20003003";// 缓装
	
	public static final String FAIL_REASON_REVERT_WO = "1000000";// 工单强行反向
	
	public static final String FAIL_REASON_REASSIGN_WO = "1000100";// 工单反向，转派其他分支
	
	// 退单原因类型
	public static final String FAIL_REASON_FOR_FAIL = "A";// 失败

	public static final String FAIL_REASON_FOR_EXP = "K";// 外勘

	public static final String FAIL_REASON_FOR_WAIT = "C";// 待装需确认

	public static final String FAIL_REASON_FOR_BIDE = "W";// 待装无需确认
	
	public static final String FAIL_REASON_FOR_WAIT_H = "Z"; // 人工岗位待装，待装转出后直接原岗后续流程，不再重复派发转待装岗位的工单
	
	public static final String FAIL_REASON_DELAY_FIX = "H";// 缓装

	public static final String FAIL_REASON_FOR_OVER = "B";// 超期

	public static final String FAIL_REASON_FOR_COLLAB = "J";// 协作单
	
	public static final String FAIL_REASON_FOR_TRANSFER = "D";// 协作单
	
	public static final String FAIL_REASON_FOR_HANDLE_RATE = "P"; //进度概述
	
	public static final String FAIL_REASON_FOR_REPAIR_WAY = "R"; //修复方法，用于服务保障系统故障修复

	// 方向标识
	public static final String STEP_REVERSE_FLAG_YES = "Y"; // 必须反向

	public static final String STEP_REVERSE_FLAG_NO = "N"; // 无须反向

	// 审核标志
	public static final String STEP_FAIL_REASON_AUDIT_FLAG_AUDIT = "Y";// 需要审核

	public static final String STEP_FAIL_REASON_AUDIT_FLAG_NOT_AUDIT = "N";// 无需审核

	// 工单异常处理标记
	public static final String STEP_FAIL_REASON_FAIL_DO_FLAG_NEED_AUDIT = "A";// 需要审核

	public static final String STEP_FAIL_REASON_FAIL_DO_FLAG_WAIT_WITHDRAW = "K";// 等待撤改

	public static final String STEP_FAIL_REASON_FAIL_DO_FLAG_FAIL_RETURN = "F";// 失败回单

	public static final String STEP_FAIL_REASON_FAIL_DO_FLAG_FAIL_STAY = "S";// 失败滞留

	public static final String STEP_FAIL_REASON_FAIL_DO_FLAG_CONTINUE = "C";// 照常流转

	public static final String STEP_FAIL_REASON_FAIL_DO_FLAG_MANUAL = "M";// 异常人工调度

	// 环节回单标志
	public static final String STEP_BACK_FLAG_ALLOW = "Y"; // 允许回单

	public static final String STEP_BACK_FLAG_REFUSE = "N";// 拒绝回单

	public static final String STEP_CANCEL_FLAG_ALLOW = "Y";// 允许撤改

	public static final String STEP_CANCEL_FLAG_REFUSE = "N";// 不允许撤改

	public static final String STEP_CANCEL_FLAG_PART = "P";// 部分状态组合容许或拒绝撤改

	// 环节对应自动工单回单限制
	public static final char STEP_MANUAL_U = 'U';// 不允许成功回单，不允许失败回单

	public static final char STEP_MANUAL_S = 'S';// 允许成功回单，不允许失败回单

	public static final char STEP_MANUAL_F = 'F';// 不允许成功回单，允许失败回单

	public static final char STEP_MANUAL_A = 'A';// 允许成功回单，允许失败回单

	// 环节回填标志
	public static final String STEP_SPEC_PRPTY_ALLOW = "Y";// 必须回填

	public static final String STEP_SPEC_PRPTY_REFUSE = "N";// 无需回填

	public static final String STEP_SPEC_PRPTY_COMPONENT_CODE_PVC = "SO_PVC"; // pvc回填控件

	public static final String STEP_SPEC_PRPTY_COMPONENT_CODE_CIRCUIT = "SO_CIRCUIT";// 电路回填控件

	public static final String STEP_SPEC_PRPTY_COMPONENT_CODE_EQPT = "SO_EQPT_PRPTY";// 终端设备回填控件

	public static final String STEP_SPEC_PRPTY_COMPONENT_CODE_MAIN_PROD_PRPTY = "SO_MAIN_PROD_PRPTY";// 主产品属性回填控件

	public static final String STEP_SPEC_PRPTY_COMPONENT_CODE_SUB_PROD_PRPTY = "SO_SUB_PROD_PRPTY";// 附属产品属性回填控件

	public static final String STEP_SPEC_PRPTY_COMPONENT_CODE_RES_FILL = "RES_FILL";// 资源后补

	public static final String STEP_SPEC_PRPTY_COMPONENT_CODE_SO_PRPTY = "SO_PRPTY";// 定单属性回填
	
	public static final String STEP_SPEC_PRPTY_FILL_MODE_A = "A";// 自动回填
	
	public static final String STEP_SPEC_PRPTY_FILL_MODE_M = "M";// 人工回填
	
	public static final String STEP_SPEC_PRPTY_FETCH_MODE_S = "S";// 静态取值
	
	public static final String STEP_SPEC_PRPTY_FETCH_MODE_D = "D";// 动态取值
	

	// 当前用户作为施工人员
	public static final String STEP_WO_STAFF_BY_SESSION = "D";

	// 根据工区选择施工人员
	public static final String STEP_WO_STAFF_BY_WORK_AREA = "W";

	// 根据包区选择施工人员
	public static final String STEP_WO_STAFF_BY_MAIN_AREA = "M";

	// 根据工区和包区选择施工人员
	public static final String STEP_WO_STAFF_BY_BOTH = "S";

	// 审核通过标志
	public static final String PASS_AUDIT = "Y";// 审核通过

	public static final String UN_PASS_AUDIT = "N";// 审核不通过

	// 系统标识
	public static final String SYSTEM_CRM = "CRM";// 综合客服系统

	public static final String SYSTEM_SPS = "SPS";// 服务开通

	public static final String SYSTEM_RMS = "RMS";// 资源系统

	public static final String SYSTEM_NAS = "NAS";// 网络激活系统
	
	public static final String SYSTEM_BILLING = "Billing";// 计费系统

	public static final String SOURCE_SYS_A = "A";// CRM
	
	public static final String SOURCE_SYS_B = "B";// 网上营业厅

	public static final int DEFAULT_PAGE_SIZE = 50;
	
	public static final int DEFAULT_PAGE_NUM = 10;

	public static final int DEFAULT_PAGE_SIZE_FOR_WORETURN = 50;// 施工处理界面每页显示记录数

	// 环节类型
	public static final String TWO = "2";// 

	// 工单类型
	public static final String WO_TYPE_FOR_RETURN = "B";// 退单

	public static final String WO_TYPE_FOR_SURVEY = "P"; // 外勘单

	public static final String WO_TYPE_FOR_BIDE = "Y"; // 待装单

	public static final String WO_TYPE_FOR_NEED_PROC = "H"; // 崔单

	public static final String WO_TYPE_FOR_COLLAB = "C"; // 协作单

	public static final String WO_TYPE_FOR_MOINITOR = "S"; // 督办单

	public static final String WO_TYPE_FOR_GO_BACK = "F"; // 追单

	public static final String WO_TYPE_FOR_NORMAL = "N"; // 正常单
	
	public static final String WO_PRESS_REMARKS="自动派发的催单";
	
	// 业务状态
	public static final String WO_BUSI_STS_NORMAL = "N";// 正常

	// 运行状态
	public static final String WO_RUN_STS_FOR_PERFORM = "P";// 处理中

	public static final String WO_RUN_STS_FOR_COMPLETED = "C";// 已完成

	public static final String WO_RUN_STS_FOR_CANCLE = "R";// 作废

	public static final String WO_RUN_STS_FOR_WAIT = "D";// 等待处理

	// 备注
	public static final String WO_REMARKS_WO_RESEND = "工单重发";

	// 锁定标志
	public static final String HALT_FLAG_FOR_UN_HALT = "N"; // 没有锁定

	// 动态标志
	public static final String DYN_FLAG_FOR_YES = "Y"; // 动态

	public static final String DYN_FLAG_FOR_NO = "N"; // 非动态

	// 派单方式
	public static final String TABLE_NAME_FOR_EXCH_ID = "E"; // 按局向

	public static final String TABLE_NAME_FOR_LOCAL_NET_ID = "L"; // 按本地网

	public static final String TABLE_NAME_FOR_AREA_ID = "A"; // 按服务区

	public static final String TABLE_NAME_FOR_SWITCH_ID = "S"; // 按交换机

	public static final String TABLE_NAME_FOR_MDF_ID = "M"; // 按配线架

	public static final String TABLE_NAME_FOR_CCP_ID = "C"; // 按照交接向

	public static final String TABLE_NAME_FOR_SERV_DEPT_ID = "D"; // 按照营维

	public static final String TABLE_NAME_FOR_SPEC_PRPTY = "P"; // 按照产品属性
	
	public static final String TABLE_NAME_FOR_MAINT_AREA_ID = "W"; // 按社区
	
	public static final String TABLE_NAME_FOR_WORK_AREA_PRPTY = "Y"; // 按属性

	// 动作类型

	public static final String ACT_TYPE_FOR_LOAD = "A"; // 装

	public static final String ACT_TYPE_FOR_REMOVE = "R"; // 拆

	public static final String ACT_TYPE_FOR_UNCHANGE = "X";// 不变(保障单为修单)
	
	public static final String ACT_TYPE_FOR_MODIFY = "M"; //修改

	// 动作类型
	public static final String AR_FLAG_LOAD = "A"; // 装

	public static final String AR_FLAG_REMOVE = "P"; // 拆

	// 主从单标志
	public static final String MAIN_FLAG_FOR_MAIN = "M"; // 主单

	public static final String MAIN_FLAG_FOR_SUITE = "S"; // 从单

	// 合单标志
	public static final String MERG_FLAG_YES = "Y"; // 已合并

	public static final String MERG_FLAG_NO = "N"; // 未合并

	public static final String MERG_FLAG_NOTNEED = "W"; // 无须合并

	// 完整性标志
	public static final String FULL_FLAG_FOR_ALL = "C"; // 从单

	// 预约通知方式
	public static final String NOTIFY_FLAG_FOR_YES = "Y"; // 已经通知

	public static final String NOTIFY_FLAG_FOR_NO = "N"; // 未通知

	// 工单处理标志

	public static final String MSG_FLAG_NO_PROCESS_ = "0"; // 没有处理

	public static final String MSG_FLAG_PROCESS_SUCCESS = "1"; // 处理成功

	public static final String MSG_FLAG_PROCESS_FAIL = "2"; // 处理失败

	public static final String WO_RET_CODE_SUCCESS = "1";// 回单成功

	public static final String WO_RET_CODE_FAIL = "0";// 回单失败
	
	// 有条件回退接口返回处理方式
	public static final String EXP_WO_DEAL_MODE_P = "P";// 只需正向
	
	public static final String EXP_WO_DEAL_MODE_R = "R";// 只需反向
	
	public static final String EXP_WO_DEAL_MODE_PR = "PR";// 正向反向都需要
	
	public static final String EXP_WO_DEAL_MODE_NPNR = "NPNR";// 既不需要正向也不需要反向 
	
	public static final String WO_ITEM_CAT_H ="H";//缓装类别
	
	public static final String WO_ITEM_CAT_Y ="W";//外堪类别
	
	public static final String WO_ITEM_STS_P ="P";//缓装转入状态
	
	public static final String WO_ITEM_STS_C ="C";//缓装转出状态
	
	public static final String WO_ITEM_STS_Y ="Y";//可以施工
	
	public static final String WO_RES_SYSTEM ="SPS";//工单表 来源系统 默认SPS



	/** *************************开通配置类*********************************** */

	// 施工因素匹配
	public static final String STEP_TEMPLATE_FOR_SCENE_R = "R"; // 有条件回退
	
	public static final String STEP_TEMPLATE_FOR_SCENE_W = "W"; // 工单因素设计
	
	public static final String STEP_TEMPLATE_FRO_SCENE_B = "B"; // 属性回填
	
	// 规格属性(spec_prpty)for_scene
	public static final String SPEC_PRPTY_FOR_SCENE_PWC = "PWC"; // 有条件回退
	
	public static final String SPEC_PRPTY_INPUT_FLAG_D = "D";//离散值
	
	public static final String SPEC_PRPTY_INPUT_FLAG_I = "I";// 输入值

	public static final String SPEC_PRPTY_INPUT_FLAG_S = "S";// 离散值（4版使用）

	// 环节类型
	public static final String STEP_TYPE_FOR_SYSTEM = "SC"; // 系统活动

	public static final String STEP_TYPE_FOR_ACCEPT = "SO"; // 受理

	public static final String STEP_TYPE_FOR_SERVICE_DEVISE = "SD"; // 服务设计

	public static final String STEP_TYPE_FOR_RESOUSE_ALLOCATE = "RA"; // 资源分配

	public static final String STEP_TYPE_FOR_SERVICE_CONFIG = "SP"; // 服务配置

	public static final String STEP_TYPE_FOR_SERVICE_ACTIVE = "SA"; // 服务激活
	
	public static final String STEP_TYPE_FOR_WORKFLOW_REBUILD ="WR"; // 流程重构
	
	public static final String STEP_TYPE_FOR_FREE_FLOW = "FF"; // 自由流程

	// 环节游离标志
	public static final String PLUS_FLAG_FOR_NORMAL = "0"; // 正常

	public static final String PLUS_FLAG_FOR_UN_NORMAL = "1"; // 非正常

	// 环节类型
	public static final String PLUS_TYPE_FOR_SURVEY = "E"; // 外堪

	public static final String PLUS_TYPE_FOR_BIDE = "W"; // 待装

	// 环节协同规则合单标志
	public static final String MERG_FLAG_FOR_NEED = "Y"; // 需要合单

	public static final String MERG_FLAG_FOR_UN_NEED = "N"; // 不合单

	// 工单合单标志
	public static final String MERG_FLAG_FOR_ALREADY_MERGE = "Y"; // 已经合单

	public static final String MERG_FLAG_FOR_NO_MERGE = "N"; // 还没有合单

	public static final String MERG_FLAG_FOR_NO_NEED_MERGE = "W"; // 不需合单

	// 匹配类型 MATCH_TYPE

	public static final String MATCH_TYPE_BUSI_RULE = "R";// R 业务规则

	public static final String MATCH_TYPE_SYS_ITEM = "P";// P 系统元素

	// 运算符 MATCH_OPERATOR
	public static final String MATCH_OPERATOR_EQUAL = "=";// = 等于，

	public static final String MATCH_OPERATOR_UNEQUAL = "!=";// != 不等于，

	public static final String MATCH_OPERATOR_MORE_THAN = ">";// > 大于，

	public static final String MATCH_OPERATOR_MORE_THAN_AND_EQUAL = ">=";// >=大于等于，

	public static final String MATCH_OPERATOR_LESS_THAN = "<";// < 小于，

	public static final String MATCH_OPERATOR_LESS_THAN_AND_EQUAL = "<=";// <=小于等于

	public static final String MATCH_OPERATOR_INCLUDE = "IN";// IN 包含，

	public static final String MATCH_OPERATOR_NOT_INCLUDE = "NOT IN";// NOT

	// 适用定单模板匹配 FOR_SO_MATCH C1 NULL Y Y 适用N 不适用
	public static final String FOR_SO_MATCH_YES = "Y";

	public static final String FOR_SO_MATCH_NO = "N";

	// 开关标志
	public static final String OPEN_FLAG_OPEN = "O";// O 启用，打开

	public static final String OPEN_FLAG_CLOSE = "C";// C 禁用，关闭

	// 是否单一产品 SIMPLE_PROD_FLAGM 多个产品间的协同。S 单个产品的协同，eg：点对点关联，蔬忙关联等。
	public static final String SAME_PROD_FLAGM_SIMPLE = "S";

	// 撤改标志 CANCEL_FLAG
	public static final String CANCEL_FLAG_YES = "Y";// 允许撤改

	public static final String CANCEL_FLAG_NO = "N";// 不允许撤改

	// 撤改状态 CANCEL_STS
	public static final String CANCEL_STS_TO_ALL = "0";// 所有状态

	public static final String CANCEL_JUDGE_RULE_INCLUDE = "I";// I

	// 包含，指STEP_CANCEL_RULE中定义的状态组合皆可撤改

	public static final String CANCEL_JUDGE_RULE_EXCLUDE = "E";// E

	// 排除，指STEP_CANCEL_RULE中定义的状态组合不可撤改

	// 互改产品业务标识
	public static final String PROD_CHANGE_FLAG_YES = "Y";

	// 环节回填属性表中，字段COMPONENT_CODE为主产品时的取值
	public static final String SO_MAIN_PROD_PRPTY = "SO_MAIN_PROD_PRPTY";

	public static final String SO_PRPTY_COMPONENT_CODE = "SO_PRPTY"; //定单流程属性

	// 环节回填属性表中，字段COMPONENT_CODE为附属产品时的取值
	public static final String SUB_PROD_PRPTY = "SO_SUB_PROD_PRPTY";

	// 环节回填属性表中，字段COMPONENT_CODE为终端设备的时的取值
	public static final String SO_EQPT_PRPTY = "SO_EQPT_PRPTY";
	
//	 环节回填属性表中，字段COMPONENT_CODE为pwc专线电路属性时的取值
	public static final String SO_PWC_PRPTY = "SO_PWC_PRPTY";

	// 环节回填属性表中，字段COMPONENT_CODE为资源候补时的取值
	public static final String RES_FILL = "RES_FILL";

	public static final String SO_CIRCUIT = "SO_CIRCUIT";

	public static final String SO_PVC = "SO_PVC";
	
	//实时并行处理开关 Y 启用 N 停用
	public static final String PARALLEL_YES = "Y";

	public static final String PARALLEL_NO = "N";

	/** *******************施工处理页面*************************************************** */
	// 施工处理页面
	public static final String Wo_RETURN = "WoReturn";// 回单页面

	public static final String WO_FETCH_UNBOOK = "WoFetchUnBook";// 领单未约页面

	public static final String WO_FETCH_BOOK = "WoFetchBook";// 领单预约页面

	public static final String WO_PRESS = "WoPress";// 催单页面

	public static final String WO_EXCEPTION = "WoException";// 异常工单页面

	public static final String WO_CC = "WoCc"; // 抄送工单页面
	
	public static final String WO_TRACK = "WoTrack";//工单跟踪
	
	/** *************工单响应弹出页面定制**************************************************** */
	public static final String WO_SUCCESS_RETURN = "PopWoSuccessReturn";//成功回单
	
	public static final String WO_SEND_TO = "PopWoSendTo";//转派
	
	public static final String WO_NOTIFY = "PopWoNotify";//短信通知
	
	public static final String WO_FAIL_RETURN = "PopWoFailReturn";//失败回单
	
	public static final String WO_FETCH_UN_BOOK = "PopWoFetchUnBook";//领单未约
	
	public static final String COMPONENT_CODE_SUCCESS_RETURN = "successReturnComp";//成功回单信息控件
	
	public static final String COMPONENT_CODE_FAIL_RETURN = "FailReturnComp";//失败回单信息控件
	
	public static final String COMPONENT_CODE_WOATTACHMENT = "woAttachmentComp";//附件上传控件
	
	public static final String COMPONENT_CODE_FETCH_UNBOOK = "fetchUnBookComp";//领单信息控件
	
	public static final String COMPONENT_CODE_FETCH_PRINT = "fetchPrintComp";//领单打印控件
	
	public static final String COMPONENT_CODE_FILL_SO_PRPTY = "fillSoPrpty";//定单属性回填控件
	
	public static final String COMPONENT_CODE_FAULT_TEST = "faultTestComp";//故障测试控件
	
	public static final String COMPONENT_CODE_FAULT_HANDLE = "faultHandleComp"; //故障处理控件
	
	public static final String COMPONENT_CODE_FAULT_CONFIRM = "faultConfirm"; //故障证实控件
	
	public static final String COMPONENT_CODE_FAULT_DETERMINE = "faultDetermineComp"; // 故障定性控件
	
	public static final String COMPONENT_CODE_ALLOW_RETURN_SO_NBR="allowReturnSoNbr"; //允许回单
	public static final String COMPONENT_CODE_SO_TEST_RESULT="soTestResultCompl";     //测试结果控件
	public static final String  COMPONENT_CODE_SA_WO_SEND_TO="saWoSendTo";         //转派控件
	public static final String  COMPONENT_CODE_WO_REASSIGN="woReassignComp";       //基于流程级工单转派控件
	
	public static final String COMPONENT_CODE_MSG_NOTIFY= "WoMsgNotifyComp"; //短信控件
	public static final String COMPONENT_CODE_WO_STAFF = "stepWoStaffComp";//环节施工人控件
	

	/** **************************sysconfig配置的类型******************************************* */
	// sysconfig配置的类型
	public static final String SYS_CONFIG_PORT_ID = "215044";// 接入设备端口id
	public static final String SYS_CONFIG_PWC_NBR_ID = "215045";// 专线号id
	public static final String SYS_CONFIG_PWC_SPEED_ID = "215046";// 速率id
	
	public static final String SYS_CONFIG_TYPE_PROVINCE = "G";// 全省统一的类型
	public static final String SYS_CONFIG_TYPE_LOCALNET = "L";// 本地网统一的类型

	public static final String SYS_CONFIG_TYPE_AREA = "A";// 服务区统一的类型

	public static final String SYS_CONFIG_TYPE_WORKAREA = "W";// 工作区统一

	public static final String SYS_CONFIG_TYPE_EXCH = "E";// 局向统一

	public static final String SYS_CONFIG_TYPE_WORKTYPE = "T";// 按工区类型

	public static final String SYS_CONFIG_EXPORT_EXCEL_RECORD = "70003"; // 导出所有页的excel记录条数

	public static final String SYS_CONFIG_EXCEL_FILEPATH = "40003"; // 导出EXCEL在服务器上的文件路径的配置

	public static final String SYS_CONFIG_BOOK_DUE_TIME = "34000";// 预约即将到期时差

	public static final String SYS_CONFIG_IS_CHECK_STEP_SPEC_PRPTY = "34010";// 回单时，是否按环节校验属性回填的必填项
	
	// add by xieyunchao 20120626，需要在统一工单系统中增加一个超链接链接到服务开通。由于吉林统一工单和服务开通用的是同一套代码，
	//为了区分是否在页面上显示超链接，特增加一个标志,如果为Y，则显示链接。
	public static final String SYS_CONFIG_SHOW_LINK = "215039";
	public static final String SYS_CONFIG_LINK_TO_SP_IP_PORT = "215040";//统一工单链接到服务开通的ip地址和端口号
	
	public static final String SYS_CONFIG_OUT_TIME = "110033";// 超时时间设置

	public static final String SYS_CONFIG_SHARE_LINE = "34004";// 共线编码

	public static final String SYS_CONFIG_MAIN_PROD = "34003";// 共线主产品

	public static final String SYS_CONFIG_NUM_RES_SERV = "34005";// 号码资源服务编码

	public static final String SYS_CONFIG_LINE_RES_SERV = "34006";// 线路资源服务编码

	public static final String SYS_CONFIG_PORT_RES_SERV = "34007";// 端口资源服务编码

	public static final String SYS_CONFIG_IP_RES_SERV = "34008";// IP资源服务编码

	public static final String SYS_CONFIG_RES_PRPTY_PHYACCNBR = "34030";// 资源属性物理号码

	public static final String SYS_CONFIG_RES_PRPTY_ACCNBR = "34031";// 资源属性逻辑号码

	public static final String SYS_CONFIG_RES_PRPTY_DISTNBR = "34032";// 资源属性逻辑号码区号

	public static final String SYS_CONFIG_RES_PRPTY_PHYDISTNBR = "34033";// 资源属性逻辑号码区号

	public static final String SYS_CONFIG_RES_PRPTY_MDFPORT = "34034";// 资源属性配线架号码

	public static final String SYS_CONFIG_RES_PRPTY_MDFEPAIR = "34035";// 资源属性配线架横列端子

	public static final String SYS_CONFIG_RES_PRPTY_MDFEIPAIR = "34036";// 资源属性回填横列入端端子

	public static final String SYS_CONFIG_RES_PRPTY_MDFEOPAIR = "34037";// 资源属性回填横列出端端子

	public static final String SYS_CONFIG_RES_PRPTY_MDFNAME = "34038";// 资源属性回填配线架名称

	public static final String SYS_CONFIG_RES_PRPTY_MDFDPAIR = "34039";// 资源属性配线架1直列端子

	public static final String SYS_CONFIG_RES_PRPTY_IPADDR = "34040";// 资源属性IP地址

	public static final String SYS_CONFIG_RES_PRPTY_SUBMASK = "34041";// 资源属性子网掩码

	public static final String SYS_CONFIG_RES_PRPTY_GATEWAY = "34042";// 资源属性网关

	public static final String SYS_CONFIG_RMS_DELAY_URL = "40000";// 后补资源地址

	public static final String SYS_CONFIG_RMS_DELAY_METHOD = "40030";// 后补资源地址

	public static final String SYS_CONFIG_PATH_EXCEL_SAVE = "40003";// excel保存路进

	public static final String SYS_CONFIG_JRE_DOWNLOADS_URL = "40005"; // jre下载地址

	public static final String SYS_CONFIG_FLASH_DOWNLOADS_URL = "40025"; // flash下载地址

	public static final String SYS_CONFIG_SLA_URL = "40017";

	public static final String SYS_CONFIG_MAINTAREA_OF_ZJ = "10000000";// 包区关联的施工平台

	public static final String SYS_CONFIG_MAINTAREA_STEP = "SP0015";// 服务开通系统异常

	public static final String SYS_CONFIG_IS_CHECK_STEP_SPEC_PRPTY_CHECK = "Y";
	
	public static final String REASON_CAT_FOR_NO_RESOURCE = "O";// 待装无资源原因

	public static final String REASON_CAT_FOR_CUSTOM_REASON = "U";// 待装客户原因
	
	public static final String WO_TYPE_FOR_BIDE_NO_RESOURCE = "R"; // 待装单（局方原因）

	public static final String WO_TYPE_FOR_BIDE_CUSTOM_REASON = "U"; // 待装单（客户原因）

	public static final String SYS_CONFIG_BIDE_WORK_AREA_ID = "34012";// 虚拟的待装工区

	public static final String SYS_CONFIG_RETURN_AUTO = "40006";// 自动工位是否可以回单

	public static final String SYS_CONFIG_FETCH_AUTO = "40007";// 自动工位是否可以领单

	public static final String SYS_CONFIG_ATTACH_SO_FLAG = "71001";// 附件上传功能开关

	public static final String SYS_CONFIG_COPY_TO_BUTTON_FLAG = "71002";// 工单抄送功能开关
	
	public static final String SYS_CONFIG_MAINTAREA_CHECK = "34018"; // 成功回单时是否进行必须维护包区校验 liyaquan
	
	public static final String SYS_CONFIG_STEP_CHECK = "34028"; //成功回单是否进行环节的校验 liyaquan
	
	public static final String SYS_CONFIG_SO_CAT_FLAG = "71013";// 是否显示保障单 longbijin
	
	public static final String SYS_CONFIG_SPEED_FLAG = "21015";//是否走计费停复实时通道 ronghengen
	
	public static final String SYS_CONFIG_PARALLEL_FLAG = "21025";//实时并行处理开关 caiqian
	
	public static final String SYS_CONFIG_SYNC_RES_PROCEDURE_FLAG = "34019";//同步资源的存储过程的开关 yangkai
	
	public static final String SYS_CONFIG_RES_ASGN_REQ_PROD_PRPTY = "34069";//资源配置请求时需要传递给资源的产品属性id
	
	//我的任务查询工单的数量
	public static final String BUSI_RULE_ID_SEND_ORDER_BY_PRPTY = "10000008";//按属性派单的规则ID; liyaquan
	
	public static final String SYS_CONFIG_TASK_NUM = "71012";

	public static final String SYS_CONFIG_ASSIGN_BY_CENTER = "34020";// 按照营维中心派单

	public static final String SYS_CONFIG_ASGN_DEVICE_FAIL_TO_EXCH = "34021";// 按设备派单失败改按局向派单

	public static final String IS_CAN_RES_CHANGE = "40010"; // 资源变更时是否需要进行校验

	public static final String SYS_CONFIG_USER_QUERY_URL = "40008";// 自动工位是否可以领单

	public static final String SYS_CONFIG_MODI_MAINTAREA = "34017"; // 可以维护包区的工作区类型

	public static final String SYS_CONFIG_CO_GROUP = "34013";// CO群下的定单是否一起启动

	public static final String SYS_CONFIG_START_PROCESS_TYPE = "45000";// 定单流程启动判断

	public static final String SYS_CONFIG_GROUP_SPEC_ID = "46000";// 具有共线关系的群组规格ID

	public static final String SYS_CONFIG_FOR_INAS = "90010";// 实时服务开通是否调用NAS模拟接口配置

	public static final String SYS_CONFIG_FOR_INAS_YES = "Y";// 实时服务开通调用NAS模拟接口配置
	
	public static final String SYS_CONFIG_IS_REWRITE_NBR = "34060";// 是否回写资源旧号码信息 shilei
	
	public static final String SYS_CONFIG_CHK_WG_WORK_TYPE = "40060"; //需要回填网格的工区(山西)

	public static final String NUM_RES_SERV = "101";// 号码资源服务编码

	public static final String LINE_RES_SERV = "301";// 线路资源服务编码

	public static final String PORT_RES_SERV = "201";// 端口资源服务编码

	public static final String IP_RES_SERV = "102";// IP资源服务编码

	public static final String RMS_DELAY_URL = "40000";// 后补资源地址

	public static final String SYS_CONFIG_MAINT_AREA_ISNEED = "34016";//  

	public static final String SYS_CONFIG_CAN_CHANGE_WO_STAFF = "40011";// 是否可以选择施工人员

	public static final String SYS_CONFIG_RES_USED_QUERY = "40013";// 在用用户资源查询地址

	public static final String SYS_CONFIG_RES_USED_CHANGE = "40014";// 在用用户资源变更地址
	
	public static final String SYS_CONFIG_RES_USED_ADDR = "215000";// 标准地址查询  add by liyaquan
	
	public static final String SYS_CONFIG_RES_CHANGE = "40026";// 资源变更配置地址
	
	public static final String SYS_CONFIG_RES_IN_PROCESS = "40027";// 在途单资源查询配置地址
	
	public static final String SYS_CONFIG_RES_CHANGE_EXCH_WO = "40033";// 资源候补局向是否取工单局向

	public static final String SYS_CONFIG_RES_DETAIL_INFO = "40015";// 资源详细信息展现地址

	public static final String SYS_CONFIG_USER_OTHER_QUERY_URL = "40016";// 在用用户其他查询

	public static final String SYS_CONFIG_NUMBER_USE_QUERY_URL = "40020";// CRM号码使用查询

	public static final String SYS_CONFIG_CRM_MOD_PWD_URL = "21008";// crm修改用户密码的地址

	public static final String SYS_CONFIG_CRM_MOD_PWD_JSP_URL = "21010";// crm在校验是否第一次登陆的时候修改密码的jsp页面地址

	public static final String SYS_CONFIG_CRM_RESET_PWD_URL = "21011";// crm

	public static final String SYS_CONFIG_SPS_QUERT_COUNT = "44000";// 增加服务开通定单查询、工单查询、施工处理高级查询的最大查询数量

	public static final String SYS_CONFIG_PORT_CONVERT_URL = "40021";// 调用资源端口对照调整表管理

	public static final String SYS_CONFIG_FILE_SIZE = "70010";// 附件上传大小限制

	public static final String SYS_CONFIG_SO_MODIFY = "71005";// 定单修改功能开启配置

	public static final String SYS_CONFIG_SO_PRIORITY = "71011";// 优先级控制功能开启配置
	
	public static final String SYS_CONFIG_IS_SHOW_MSG = "71020";// 短信派单默认是否显示消息界面
	
	public static final String SYS_CONFIG_SHOW_MSG = "71021";// 领单页面是否显示发送消息的功能
	
	public static final String SYS_CONFIG_CHECK_CODE = "21030";// 登陆页面是否需要校验码
	
	public static final String SYS_CONFIG_CHECK_CODE_YES = "Y";// 
	
	public static final String SYS_CONFIG_CHECK_CODE_NO = "N";// 
	
	//public static final String WO_ATTACHMENT_COMP = "woAttachmentComp";// 附件上传控件

	public static final String SYS_CONFIG_PRESSPRECENT = "71022"; //催单派发TIMER派发催单时间间隔比例

	public static final String SYS_CONFIG_ISCREATEPRESSWO = "71023"; //催单派发TIMER是否生成催单,还是直接派发短信
	
	// 超期工单派发短信，超过预警时间是否派发短信
    public static final String SYS_CONFIG_NRMS_IS_QUERY_PRE_ALARM_DATE = "80030";

	// 密码重置的方法地址

	public static final String SYS_CONFIG_FIRST_PASSWORD = "21000";// 系统初始化密码12ab!@

	public static final String SYS_CONFIG_STEP_NOT_CANCLE_OR_CHANGE = "40017";// 如果流程已经经过某些环节，则不允许撤改

	public static final String SYS_CONFIG_FILTER_USER = "50003";// 过滤用户

	public static final String SYS_CONFIG_IS_SYN_PAY_CHARGE = "40019"; // 是否调用CRM收费状态同步

	public static final String SYS_CONFIG_IS_SYN_PAY_CHARGE_FOR_NO = "N";

	public static final String SYS_CONFIG_TIMER_REALTIMEFRESH = "90001"; // Timer实时回填信息

	public static final String SYS_CONFIG_IS_EJB_REMOTE = "21014"; // 设置系统ejb调用方式

	public static final String SYS_CONFIG_RELEASED_VERSION = "90002"; // 服务开通发布版本

	public static final String SYS_CONFIG_CHG_SERV_SPEC_LIST = "35000"; // 资源查询返回插入新旧两条记录的客户服务列表

	public static final String SYS_CONFIG_RES_RELEASE_STEP = "90003"; // 允许进行资源释放的环节列表

	public static final String SYS_CONFIG_GROUP_CHANGE_FAILRETURN_REASON_ID = "90004"; // "群组变更关联成员定单退回营业"异常原因ID

	public static final String SYS_CONFIG_ROUTER_CHECK = "33000"; // 成功回单时是否进行路由设计校验

	public static final String SYS_CONFIG_CUSTOM_NAME_HB_CUCC = "100000"; // 客户名称,
																			// 用于针对不同的客户产品,区分功能和风格

	public static final String SYS_CONFIG_CUSTOM_TYPE = "99999"; // 类别
																	// CUCC:中国联通
																	// CTG：中国电信

	public static final String SYS_CONFIG_SYS_INFO_NUM = "90005";// 系统公告,用于控制系统消息显示的条数.

	public static final String SYS_CONFIG_RELEASED_SMALL_VERSION = "90006"; // 服务开通发布小版本
	
	public static final String SYS_CONFIG_SPS_URL = "22000";// SPS的URL

	public static final String SYS_CONFIG_SGS_URL = "22001";// SGS的URL

	public static final String SYS_CONFIG_WORKFORCE_URL = "22002";// WORKFORCE的URL

	public static final String SYS_CONFIG_INAS_URL = "22003";// INAS的URL

	public static final String SYS_CONFIG_RMS_URL = "22004";// RMS的URL

	public static final String SYS_CONFIG_PROCESS_ID = "36000";// 自由流设计默认流程ID

	public static final String SYS_CONFIG_EXP_DATE = "36001";// 定单流程默认失效时间
	
	public static final String SYS_CONFIG_MAIL_HOST_NAME = "81010";//配置邮件服务器名称
	
	public static final String SYS_CONFIG_SEND_MAIL_NAME = "81020";//配置发送者邮件地址
	
	public static final String SYS_CONFIG_INTERFACE_LOCAL_VISION = "82010"; // 各地接口版本配置
	
	public static final String SYS_CONFIG_FLEX_WEBSTART = "40100";// 流程拓扑相关展现方式webstart/flex控制开关
	
	public static final String SYS_CONFIG_FLEX = "FLEX";//流程拓扑相关展现方式:flex

	public static final String SYS_CONFIG_FIND_HIS = "36002";// 定单查询、工单查询的历史查询条件选项是否显示
	
	public static final String SYS_CONFIG_ASSIGN_CCP = "3000008";// 一级交接箱id
	
	public static final String SYS_CONFIG_ASSIGN_CCP_NAME = "3000010";// 一级交接箱名称
	public static final String SYS_CONFIG_ASSIGN_ZGDL = "3000021";// 主干电缆编号
	
	public static final String SYS_CONFIG_ASSIGN_DEVICETYPE = "200";// 按交接箱派单
	
	public static final String SYS_CONFIG_ASSIGN_DEVICESUBTYPE = "20021";// 按交接箱派单

	/** ************************************************SEQ********************************************* */

	// SEQ
	
	public static final String SERV_DEPT_STOP_CONFIG_ID_SEQ = "SERV_DEPT_STOP_CONFIG_ID";

	public static final String ATTACHMENT_ID_SEQ = "ATTACHMENT_ID";//按属性派单 add by caiqian 20100224
	
	public static final String WORK_AREA_PRPTY_ID_SEQ = "WORK_AREA_PRPTY_ID";//按属性派单 add by liyq 2009-9-22
	
	public static final String MSG_ITEM_ID_SEQ = "MSG_ITEM_ID";// 消息组成元素
	
	public static final String NWORK_DAY_ID_SEQ = "NWORK_DAY_ID";// 节假日
	
	public static final String MATERIAL_ID_SEQ = "MATERIAL_ID"; // 材料回填
	public static final String MSG_SEQ = "MSG";// 消息组成元素
	public static final String LOGIN_LOG_ID = "LOGIN_LOG_ID"; // 材料回填

	public static final String SO_TEMPLATE_ID_SEQ = "SO_TEMPLATE_ID";

	public static final String PKG_GRP_RELA_ID_SEQ = "PKG_GRP_RELA_ID";

	public static final String INTER_CALL_CONFIG_ID_SEQ = "INTER_CALL_CONFIG_ID";

	public static final String SO_MATCH_ID_SEQ = "SO_MATCH_ID";

	public static final String SO_PROCESS_ID_SEQ = "SO_PROCESS_ID";

	public static final String FAIL_REASON_ID_SEQ = "FAIL_REASON_ID";// 异常原因配置

	public static final String STEP_REASON_ID_SEQ = "STEP_REASON_ID";
	
	public static final String STEP_TEMPLATE_ID_SEQ = "STEP_TEMPLATE_ID";//施工模板匹配

	public static final String STEP_FACTOR_ID_SEQ = "STEP_FACTOR_ID";

	public static final String COLLAB_RULE_ID_SEQ = "COLLAB_RULE_ID";// 协同规则序列

	public static final String COLLAB_RULE_BUSI_ID_SEQ = "COLLAB_RULE_BUSI_ID";// 协同规则业务序列

	public static final String WORK_TYPE_ID_SEQ = "WORK_TYPE_ID";// 工区类型序列

	public static final String WO_HANDLE_CUSTOM_SEQ = "WO_HANDLE_CUSTOM";// 施工处理界面定制序列

	public static final String QUERY_CONFIG_ID_SEQ = "QUERY_CONFIG_ID";// 查询条件

	public static final String INTER_SERVICE_ORDER_ID_SEQ = "INTER_SERVICE_ORDER_ID";// 服务定单接口表序列

	public static final String PROCESS_COLLAB_RULE_ID_SEQ = "PROCESS_COLLAB_RULE_ID";// 流程协同规则编号

	public static final String STEP_COLLAB_RULE_ID_SEQ = "STEP_COLLAB_RULE_ID";// 环节协同规则编号

	public static final String SO_BOOK_ID_SEQ = "SO_BOOK_ID";// 定单预约编号

	public static final String SO_CHARGE_ID_SEQ = "SO_CHARGE_ID";// 定单费用信息编号

	public static final String SPJK_PARA_ID_SEQ = "SPJK_PARA_ID"; // 接口参数编号

	public static final String WO_NBR_SEQ = "WO_NBR";// 工单号码
	
	

	public static final String CUST_ORDER_ITEM_ID_SEQ = "CUST_ORDER_ITEM_ID";// 客户订单明细

	public static final String CUST_ORDER_BUSINESS_ID_SEQ = "CUST_ORDER_BUSINESS_ID";// 客户订单与服务定单关系

	public static final String SO_SI_GROUP_MEMBER_ID_SEQ = "SO_SI_GROUP_MEMBER_ID";// 定单服务实例组成员

	public static final String SO_SI_GROUP_ID_SEQ = "SO_SI_GROUP_ID";// 定单服务实例组

	public static final String SO_RELA_ID_SEQ = "SO_RELA_ID";// 定单关联信息

	public static final String SO_MAIN_PROD_ID_SEQ = "SO_MAIN_PROD_ID";// 定单主产品信息

	public static final String SO_PRICE_PLAN_ID_SEQ = "SO_PRICE_PLAN_ID";// 定单价格计划编号

	public static final String SO_SUB_PROD_ID_SEQ = "SO_SUB_PROD_ID";// 定单附属产品信息编号

	public static final String SO_ACC_NBR_ID_SEQ = "SO_ACC_NBR_ID";// 定单号码实例编号

	public static final String SO_ADDR_ID_SEQ = "SO_ADDR_ID";// 定单地址编号

	public static final String SO_CHANGED_ID_SEQ = "SO_CHANGED_ID"; // 定单变动信息编号

	public static final String SO_EQPT_PROD_ID_SEQ = "SO_EQPT_PROD_ID";// 定单终端设备信息编号

	public static final String SO_EQPT_PRPTY_ID_SEQ = "SO_EQPT_PRPTY_ID";// 定单终端设备属性信息编号
	
	public static final String SO_PRPTY_ID_SEQ = "SO_PRPTY_ID";//定单属性编号

	public static final String SO_MAIN_PROD_PRPTY_ID_SEQ = "SO_MAIN_PROD_PRPTY_ID";// 定单主产品属性信息编号

	public static final String SO_MAIN_PROD_PRPTY_EXT_ID_SEQ = "SO_MAIN_PROD_PRPTY_EXT_ID";// 主产品属性扩展编号

	public static final String SO_SUB_PROD_PRPTY_ID_SEQ = "SO_SUB_PROD_PRPTY_ID";// 定单附属产品属性信息编号

	public static final String SO_SUB_PROD_PRPTY_EXT_ID_SEQ = "SO_SUB_PROD_PRPTY_EXT_ID";// 定单附属产品属性扩展编号

	public static final String SO_PROD_PAUSE_ID_SEQ = "SO_PROD_PAUSE_ID";// 定单产品停复信息编号

	public static final String SO_CUST_ID_SEQ = "SO_CUST_ID";// 定单客户信息编号

	public static final String WO_HANDLE_SEQ = "WO_HANDLE_ID";// 工单操作记录表

	public static final String MSG_ID_SEQ = "MSG_ID";// 定单匹配消息 SO_MSG_CENTER

	public static final String ACTION_LOG_SEQ = "ACTION_ID";// 定单匹配消息

	public static final String INTER_MSG_CENTER_ID_SEQ = "INTER_MSG_ID";// 定单解析消息表

	public static final String STEP_COLLAB_INST_ID_SEQ = "STEP_COLLAB_INST_ID";// 环节协调规则实例编号

	public static final String PROCESS_COLLAB_INST_ID_SEQ = "PROCESS_COLLAB_INST_ID";// 流程协调规则实例编号

	public static final String SO_SI_GROUP_MEM_PRPTY_ID_SEQ = "SO_SI_GROUP_MEM_PRPTY_ID";// 定单服务实例组成员属性编号

	public static final String SO_RES_ID_SEQ = "SO_RES_ID";// 定单资源实例编号

	public static final String RES_NWKSEG_ID_SEQ = "RES_NWKSEG_ID";// 资源网段编号

	public static final String XMLHEADER_SERIALNO_FOR_RMS_SEQ = "XMLHEADER_SERIALNO_FOR_RMS";// 资源接口操作序列

	public static final String SO_NBR_SEQ = "SO_NBR";// 定单号码

	public static final String SO_IP_ADDR_ID_SEQ = "SO_IP_ADDR_ID";// IP地址信息编号

	public static final String SO_USERNAME_ID_SEQ = "SO_USERNAME_ID";// 定单帐户密码编号

	public static final String USER_DATA_RANGE_ID_SEQ = "USER_RANGE_ID";// 用户数据权限编码

	public static final String REQ_DATE_SMS_ID_SEQ = "REQ_DATE_SMS_ID";// 预约服务短信信息主键

	public static final String STEP_RES_SERV_ID_SEQ = "STEP_RES_SERV_ID";// 环节资源服务

	public static final String PROD_RES_SERV_ID_SEQ = "PROD_RES_SERV_ID";// 产品资源服务

	public static final String SO_HANDLE_ID_SEQ = "SO_HANDLE_ID";// 定单处理过程标识

	public static final String SO_SI_GROUP_NO_SEQ = "SO_SI_GROUP_NO";// 定单服务实例组编号

	public static final String SO_AUTH_ID_SEQ = "SO_AUTH_ID";// 认证信息编号

	public static final String SO_TEL_GUIDE_ID_SEQ = "SO_TEL_GUIDE_ID";// 定单电话导航信息编号

	public static final String SO_PVC_ID_SEQ = "SO_PVC_ID";// 订单服务PVC电路信息

	public static final String SO_CIRCUIT_ID_SEQ = "SO_CIRCUIT_ID";// 电路信息编号

	public static final String STEP_CONFIG_ID_SEQ = "STEP_CONFIG_ID"; // 环节配置编号

	public static final String STEP_CANCEL_RULE_ID_SEQ = "STEP_CANCEL_RULE_ID"; // 撤改规则编码

	public static final String WORK_TYPE_TAB_NAME_ID_SEQ = "WORK_TYPE_TAB_NAME_ID";// 派单方式

	public static final String NOTIFY_TYPE_ID_SEQ = "NOTIFY_TYPE_ID";// 通知类型ID

	public static final String MSG_SEND_RULE_ID_SEQ = "MSG_SEND_RULE_ID";// 消息发送策略

	public static final String REAL_TIME_BUSI_CONFIG_ID_SEQ = "REAL_TIME_BUSI_CONFIG_ID";// 定单实时控制

	public static final String SPEC_PRPTY_ID_SEQ = "SPEC_PRPTY_ID";

	public static final String BUSINESS_ID_SEQ = "BUSINESS_ID";

	public static final String CHG_SERV_SPEC_ID_SEQ = "CHG_SERV_SPEC_ID";

	public static final String PRPTY_PARA_ID_SEQ = "PRPTY_PARA_ID";// 参数配置取值方法

	public static final String PARA_VALUE_MAP_ID_SEQ = "PARA_VALUE_MAP_ID";// 参数配置值域映射

	public static final String ORDER_PARA_ID_SEQ = "ORDER_PARA_ID";// 命令参数编号

	public static final String PROD_ACTION_STEP_ID_SEQ = "PROD_ACTION_STEP_ID";// 命令参数编号

	public static final String WF_EXPRESS_ID_SEQ = "WF_EXPRESS_ID";// 条件表达式

	public static final String WF_PARAMETER_ID_SEQ = "WF_PARAMETER_ID";// 条件参数

	public static final String WORK_AREA_OBJ_ID_SEQ = "WORK_AREA_OBJ_ID";// 非局向工区配置

	public static final String MSG_TEMPLATE_ID_SEQ = "MSG_TEMPLATE_ID";// 消息模板配置

	public static final String SO_ATTACH_ID_SEQ = "SO_ATTACH_ID"; // 定单附件

	public static final String WO_DESIGN_ID_SEQ = "WO_DESIGN_ID"; // 施工设计

	public static final String WO_DESIGN_DETAIL_ID_SEQ = "WO_DESIGN_DETAIL_ID"; // 施工设计详细信息

	public static final String STEP_DESIGN_MATCH_ID_SEQ = "STEP_DESIGN_MATCH_ID";// 环节设计匹配要素

	public static final String STEP_DESIGN_ID_SEQ = "STEP_DESIGN_ID";// 环节设计
	
	public static final String WO_FACTOR_INST_ID_SEQ = "WO_FACTOR_INST_ID";
	
	public static final String WO_ITEM_ID_SEQ = "WO_ITEM_ID";// WO_ITEM.WO_ITEM_ID
	
	public static final String WF_PROC_NODE_ACTIVITY_SEQ = "WF_PROC_NODE_ACTIVITY_ID";

	public static final String PARSE_CONFIG_ID_SEQ = "PARSE_CONFIG_ID";//解析配置内容编码
	public static final String WORK_REPORT_ID_SEQ = "WORK_REPORT_ID";//巡检报告
	
	public static final String SO_TEST_RESULT_ID_SEQ = "SO_TEST_RESULT_ID";//障碍测试结果
	
	public static final String SO_TEST_RESULT_ITEM_ID_SEQ = "SO_TEST_RESULT_ITEM_ID";//障碍测试结果项信息
	
	/** 产品包与产品关系表序列号 */
	public static final String TRADE_PROD_ID_SEQ = "TRADE_PROD_ID";// 产品包与产品关系表ID号
	public static final String TRADE_ID_SEQ = "TRADE_ID";

	/** PM CRM集成过来的 */
	public static final String PROD_TREE_ID_SEQ = "PROD_TREE_ID";// 产品提供树

	public static final String WO_GROUP_ID_SEQ = "WO_GROUP_ID";// 工单组标识

	public static final String WO_GROUP_MEMBER_ID_SEQ = "WO_GROUP_MEMBER_ID";// 工单组成员标识

	public static final String SSO_TEMP_SESSION_ID_SEQ = "SSO_TEMP_SESSION_ID";// 单点登陆

	public static final String RES_XML_MSG_ID_SEQ = "RES_XML_MSG_ID";
	
	public static final String SO_SLA_ID_SEQ = "SO_SLA_ID";//  
	
	public static final String SO_PWC_ID_SEQ = "SO_PWC_ID";//  专线电路
	
	public static final String SO_PWC_PRPTY_ID_SEQ = "SO_PWC_PRPTY_ID";//  专线电路属性
	
	public static final String MAINT_WORK_AREA_ID_SEQ = "MAINT_WORK_AREA_ID";//维护区和工区关系标识
	

	/** *************************工作流所用******************************** */
	public static final String WF_ACTIVITY_SEQ = "ACTIVITY_ID"; // 活动SEQ（工作流所用的）

	/** ************************监控/KPI部分 add at 08.04.02 by huangyx************ */
	public static final String CHECK_RULE_ID_SEQ = "CHECK_RULE_ID"; // 监控规则

	public static final String SERVER_APPLICATION_ID_SEQ = "SERVER_APPLICATION_ID"; // 后台应用

	public static final String PROCESS_STATUS_ID_SEQ = "PROCESS_STATUS_ID"; // 进程状态

	public static final String THREAD_STATUS_ID_SEQ = "THREAD_STATUS_ID"; // 线程状态

	/** *******************工作流接口参数*********************************** */
	// 事件
	public static final String PROC_START = "PROC_START";

	public static final String PROC_INST_START = "PROC_INST_START";

	public static final String PROC_PAUSE = "PROC_PAUSE";

	public static final String PROC_CANCEL = "PROC_CANCEL";

	public static final String PROC_CONTINUE = "PROC_CONTINUE";

	public static final String WORKITEM_SUCCESS = "WORKITEM_SUCCESS";

	public static final String WORKITEM_FAIL = "WORKITEM_FAIL";

	public static final String RELATION_CANCEL = "RELATION_CANCEL";
	// 过程关系类型
	public static final String RELA_TYPE_FOR_MODIFY = "3";

	public static final String RELA_TYPE_FOR_REMOKE = "4";

	// 工作流回单接口调用返回值
	public static final String RET_CODE_DO_SUCCESSED = "0";// 处理成功 （包括工作项和流程）

	// 流程类
	public static final String RET_CODE_PROC_INEXIST = "1";// 流程不存在

	// 工作项类
	public static final String RET_CODE_STOP_CURRENT_STEP = "2";// 停留当前环节（异常处理类型为滞留当前环节）

	public static final String RET_CODE_EXCEPTION_PROCE_INEXIST = "3";// 查询不到异常流程

	public static final String RET_CODE_RETURN_START_SYSTEM = "4"; // 返回发起系统（异常流程处理类型为回退到发起系统）

	public static final String RET_CODE_REVERSE_WORKITEM_NOFAILED = "5";// 反向工作项不能失败回单

	public static final String RET_CODE_REVERSE_EXEC_SUCCESSFUL = "6";// 当前工作项已经成功执行，不能够再次回单。

	// 协同关系类型

	public static final String COLLAB_TYPE_C_S = "C->S";// 结束－－开始

	public static final String COLLAB_TYPE_S_S = "S->S";// 开始－－开始

	public static final String COLLAB_TYPE_C_C = "C->C";// 结束－－结束

	public static final String COLLAB_TYPE_SSCC = "SSCC";// 同时开始，同时结束

	public static final String COLLAB_TYPE_SS = "SS";// 同时开始

	// 实时标志
	public static final String REAL_TIME = "Y";// 实时

	public static final String UN_REAL_TIME = "N";// 非实时

	public static final String WF_REALTIME_TRUE = "1"; // 实时

	public static final String WF_REALTIME_FALSE = "0"; // 非实时
	
	public static final String WF_REAL_TIME_FLAG = "WF_REAL_TIME_FLAG";

	// 查询条件的页面数设置
	public static final String ROWS_PER_PAGE = "20";

	// 服务开通返回工作流参数说明
	public static final String DISPATCH_RESULT_KEY = "DISPATCH_RESULT";// 工作项派发返回结果的键值

	public static final String EXEC_SUCCESS = "EXECUTE_OK"; // 执行成功

	public static final String DISPATCH_SUCCESS = "DISPATCH_OK"; // 派发成功

	public static final String TOTE_OBJECT = "TOTE_OBJECT";// 业务系统需要工作流携带对象的键值

	// 工作调用服务开通流参数说明

	public static final String INTERFACE_CONFIG_WORKITEM_XML_KEY = "WORKITEM_XML";

	public static final String INTERFACE_CONFIG_WORKITEM_ID_KEY = "WORKITEM_ID";

	public static final String INTERFACE_CONFIG_SO_NBR_KEY = "SO_NBR"; //

	public static final String INTERFACE_CONFIG_EXT_SO_NBR_KEY = "EXT_SO_NBR";

	public static final String INTERFACE_CONFIG_LOCK_STATE_KEY = "LOCK_STATE";

	public static final String INTERFACE_CONFIG_SO_SEQ_KEY = "SO_SEQ";

	public static final String INTERFACE_CONFIG_PROC_LIMIT_DATE_KEY = "PROC_LIMIT_DATE"; // 流程告警时限

	public static final String INTERFACE_CONFIG_PROC_PRE_LIMIT_DATE_KEY = "PROC_PRE_LIMIT_DATE"; // 流程预警时限

	public static final String INTERFACE_CONFIG_PROC_INST_ID_KEY = "PROC_INST_ID";

	public static final String INTERFACE_CONFIG_WO_NBR_KEY = "WO_NBR";

	public static final String INTERFACE_CONFIG_PROC_NODE_ID_KEY = "PROC_NODE_ID";

	public static final String INTERFACE_CONFIG_STAFF_ID_KEY = "STAFF_ID";

	public static final String INTERFACE_CONFIG_WO_RUN_STS_KEY = "RUN_STS";

	public static final String INTERFACE_CONFIG_METHOD_NAME_KEY = "METHOD_NAME";

	public static final String INTERFACE_CONFIG_WO_WO_STAFF_ID_KEY = "WO_STAFF_ID";

	public static final String INTERFACE_CONFIG_WO_RT_STAFF_ID_KEY = "RT_STAFF_ID";

	public static final String INTERFACE_CONFIG_WO_REMARKS_KEY = "REMARKS";
	
	public static final String INTERFACE_CONFIG_STEP_ID = "STEP_ID";
	
	public static final String INTERFACE_CONFIG_STEP_DESC = "STEP_DESC";
	
	public static final String INTERFACE_CONFIG_STEP_LIST = "STEP_LIST";
	
	public static final String INTERFACE_CONFIG_REFACTOR_NODE_INST_ID = "REFACTOR_NODE_INST_ID";
	
	public static final String INTERFACE_CONFIG_REFACTOR_NODE_INST_DESC = "REFACTOR_NODE_INST_DESC";
	
	public static final String INTERFACE_CONFIG_WO_RUN_STS = "WO_RUN_STS";

	/*----------------------------------接口调用方法名---------------------------------------------*/

	public static final String INTERFACE_CONFIG_METHOD_WO_RESEND = "woResend";

	public static final String INTERFACE_CONFIG_METHOD_QUERY_WO_IN_PREFORM = "queryWoInPerform";

	public static final String INTERFACE_CONFIG_METHOD_WO_BACK = "woBack";

	public static final String INTERFACE_CONFIG_METHOD_WO_REVERSE = "woReverse";

	public static final String INTERFACE_CONFIG_METHOD_HAS_COLLAB_WO = "hasCollabWo";

	// 重新計算時限
	public static final String LIMIT_DATE = "LIMIT_DATE";// 告警时间

	public static final String PRE_LIMIT_DATE = "PRE_LIMIT_DATE";// 预警时间

	// 状态同步参数说明：
	public static final String SYNC_STS_PS_RUNNING = "PS_RUNNING"; // 流程启动运行

	public static final String SYNC_STS_PS_FINISH = "PS_FINISH"; // 流程运行结束

	public static final String SYNC_STS_PS_WAIT = "PS_WAIT"; // 流程同步等待状态

	public static final String SYNC_STS_NS_WAIT = "NS_WAIT"; // 环节同步等待状态

	public static final String SYNC_STS_PS_CONTINUE = "PS_CONTINUE"; // 取消同步等待接口

	//
	public static final String SYNC_TYPE_KEY = "SYNC_TYPE"; // 键值

	/** *******************CRM接口参数*********************************** */
	public static final String AUTHOR_VALUE = "Y";

	// 新旧标志 NO_FLAG
	public static final String NO_FLAG_NEW = "A";// A 新

	public static final String NO_FLAG_OLD = "P";// P 旧

	public static final String NO_FLAG_NEW_AND_OLD = "AP";// AP 新旧，改、移

	// 客户来源 CUST_SOURCE
	public static final String CUST_SOURCE_BY_SELF = "A";// A 自我发展

	public static final String CUST_SOURCE_BY_RIVAL = "B";// B 竞争对手

	// 社区经理确认标志 CONFIG_FLAG
	public static final String CONFIG_FLAG_YES = "0";// 0:没有确认

	public static final String CONFIG_FLAG_NO = "1";// 1:已经确认

	// 预约变更次数 BOOK_COUNT
	public static final String BOOK_COUNT_DEFAULT = "0";

	public static final String BOOK_POINT_FOR_FRONT = "F";// 前台预约

	public static final String BOOK_POINT_FOR_BACKGROUND = "B";// 后台预约

	public static final String DEST_NBR = "34009";

	public static final String BOOK_STEP_ID = "34002";

	public static final String MSG_TYPE_FOR_NEW_BOOK = "0";

	public static final String MSG_TYPE_FOR_CANCLE_BOOK = "1";

	public static final String MSG_TYPE_FOR_CANCLE_SO = "2";

	public static final String MSG_TYPE_FOR_CHANGE_BOOK = "3";

	// 客户订单（CUST_ORDER）状态 STS
	public static final String CUST_ORDER_STS_NEW = "A";// A 新建

	public static final String CUST_ORDER_STS_ACC_ING = "B";// B 受理中

	public static final String CUST_ORDER_STS_ACC_ED = "C";// C 已受理

	public static final String CUST_ORDER_STS_DISUSE = "D";// D 作废

	public static final String CUST_ORDER_STS_FINISH = "E";// E 已竣工

	public static final String CUST_ORDER_STS_SEPARATE = "R";// R 离包

	// 客户订单与服务定单关系（CUST_ORDER_BUSINESS）
	public static final String CUST_ORDER_BUSINESS_USE = "A";// 再用

	public static final String CUST_ORDER_BUSINESS_UNUSE = "D";// 作废

	public static final String CUST_ORDER_BUSINESS_SEPARATE = "R";// 离包

	// 定单接收状态（inter_serviece_ORDER 状态 ）
	public static final String STS_ACCEPT_SUCCESS = "A";// 定单接收成功

	public static final String STS_ANALYSIS_LOCK = "B";// 定单解析锁定

	public static final String STS_AFREASH_LOCK = "F";// 定单解析锁定

	public static final String STS_ANALYSIS_SUCCESS = "C";// 定单解析成功

	public static final String STS_ANALYSIS_FAIL = "D";// 定单解析失败

	public static final String STS_RETURN_ERROR_SUCCESS = "Z";// 数据错误返回CRM成功

	public static final String STS_RETURN_ERROR_FAILED = "Y";// 数据错误返回CRM失败

	public static final String STS_MATCH_FAILED = "E";// E定单匹配失败

	public static final String STS_PROCCESSING = "P";// P流程正常启动

	public static final String STS_PROC_FINISH_NOTIFY_SUCCESS = "H";// 正常竣工通知CRM成功

	public static final String STS_PROC_FINISH_NOTIFY_FAIL = "I";// 正常竣工通知CRM失败

	public static final String STS_CANCEL_SO_NOTITY_SUCCESS = "J";// 撤单完成通知CRM成功

	public static final String STS_CANCEL_SO_NOTITY_FAIL = "K";// 撤单完成通知CRM失败

	public static final String STS_RETURN_SO_NOTITY_SUCCESS = "L";// 施工失败退回CRM成功

	public static final String STS_RETURN_SO_NOTITY_FAIL = "M";// 施工失败退回CRM失败

	// 客户订单明细订购类型 ITEM_TYPE
	public static final String ITEM_TYPE_PROD_NEW = "A";// A 产品新购

	public static final String ITEM_TYPE_SERV_CHANGE = "B";// B 服务变更

	// 自我标志
	public static final String IS_SELF_YES = "Y";// Y 是

	public static final String IS_SELF_NO = "N";// N 否，关联的

	// 人工施工岗类型
	public static final String MAN_WORK_TYPE_ID = "6";// Y 是

	// isSelf 0:关联实例 1:实例本身 2:改前产品实例
	public static final String IS_SELF_REL = "0";

	public static final String IS_SELF_SEL = "1";

	public static final String IS_SELF_BEF = "2";

	// 变更标志 Y 有变更 N 未变更
	public static final String CHANGED_FLAG_YES = "Y";

	public static final String CHANGED_FLAG_NO = "N";

	// 变更标志 Y 有变更 N 未变更
	public static final String TWO_EXCH_FLAG_YES = "Y";

	public static final String TWO_EXCH_FLAG_NO = "N";

	// 主副标志 IS_MAIN Y 主产品 N 附属产品
	public static final String IS_MAIN_YES = "Y";

	public static final String IS_MAIN_NO = "N";

	// 定单接收状态
	public static final String SO_ANALYSIS_SECCESS = "C";

	public static final String SO_ANALYSIS_FAIL = "D";

	// 定单类型
	public static final String RELATED_TYPE_FUTILITY = "1";// 注销单

	public static final String RELATED_TYPE_MODIFY = "2";// 修改单

	public static final String RELATED_TYPE_NORMOY = "0";// 正常单

	public static final String RELATED_TYPE_FOR_WAIT = "3";// 缓装单

	public static final String RELATED_TYPE_FOR_AGAIN = "4";// 开装单

	// 定单返回给CRM结果状态
	public static final String RESULT_CODE_SUCCESS = "0";// 成功

	public static final String RESULT_CODE_FAIL = "1";// 失败

	// 撤单，改单结果
	public static final String AUDIT_PASS = "0";// 成功

	public static final String AUDIT_UN_PASS = "1";// 失败

	public static final String AUDIT_PASS_FINISH = "2"; // 撤改单完成

	// 回传CRM订单返回类型

	public static final String SO_RETURN_TYPE_FINISH = "C";// 竣工返回

	public static final String SO_RETURN_TYPE_BACK = "T";// 退单返回

	public static final String SO_RETURN_TYPE_FAIL = "F";// 解析失败返回

	public static final String SO_RETURN_TYPE_REMOVE = "E";// 撤单完成返回

	// 撤单类型
	public static final String EXPT_TYPE_SO_MODIFY = "2";// 修改单

	public static final String EXPT_TYPE_FUTILITY = "1";// 注销单

	public static final String EXPT_TYPE_RE_RECEIVE = "6";// 重发单

	public static final String EXPT_TYPE_RELE_RES = "7";// 资源释放

	// 定单是否到达 SO_PARSE_FLAG
	public static final String SO_PARSE_FLAG_YES = "Y";// 定单已经到达SPS

	public static final String SO_PARSE_FLAG_NO = "N";// 定单还未到SPS

	public static final String SO_SI_GROUP_OLD = "OLD";// 定单服务实例组

	public static final String SO_SI_GROUP_NEW = "NEW";// 定单服务实例组

	// 是否取消预约标志
	public static final String CANCLE_BOOK_YES = "Y"; //

	public static final String CANCLE_BOOK_NO = "N";

	// 是否释放资源标志
	public static final String UNCHAIN_SO_SI_GROUP_YES = "Y";

	public static final String UNCHAIN_SO_SI_GROUP_NO = "N";

	public static final String SO_SI_GROUP_ACT_TYPE_MODIFY = "M";

	public static final String SO_SI_GROUP_ACT_TYPE_NEW = "A";

	public static final String SO_SI_GROUP_ACT_TYPE_DEL = "R";

	public static final String ACT_TYPE_FROM_CRM_HIS = "P";

	// 定单变更回填表
	public static final String SO_CHANGE_FOR_SO_ADDR = "SO_ADDR";// 定单地址信息表
	
	public static final String SO_CHANGE_FOR_SO_IP_ADDR = "SO_IP_ADDR";// 定单IP地址信息表

	public static final String SO_CHANGE_FOR_SO_ACC_NBR = "SO_ACC_NBR";// 定单号码信息表

	public static final String SO_CHANGE_FOR_SO_PVC = "SO_PVC";// 定单SO_PVC信息表

	public static final String SO_CHANGE_FOR_SO_CIRCUIT = "SO_CIRCUIT";// 定单电路信息表

	public static final String SO_CHANGE_FOR_SO_MAIN_PROD_PRPTY = "SO_MAIN_PROD_PRPTY";// 定单主产品属性信息表

	public static final String SO_CHANGE_FOR_SO_SUB_PROD_PRPTY = "SO_SUB_PROD_PRPTY";// 定单附属产品属性信息表

	public static final String SO_CHANGE_FOR_SO_EQPT_PRPTY = "SO_EQPT_PRPTY";// 设备属性信息表
	
	public static final String SO_CHANGE_FOR_SO_PWC_PRPTY = "SO_PWC_PRPTY";// pwc专线电路属性信息
	
	public static final String SO_CHANGE_FOR_SO_BOOK = "SO_BOOK";// 定单预约信息

	// 定单号码需要特殊处理的产品客与户服务
	public static final String PROD_ID_FOR_TEL = "10000000";// 普通电话

	public static final String PROD_ID_FOR_ADSL = "20001001";// ADSL虚拟拨号

	public static final String PROD_ID_FOR_PHS = "15000000";// 无线市话

	public static final String GROUP_SPEC_ID_FOR_SHARE_LINE = "2";// 共线群组

	public static final String CHG_SERV_SPEC_ID_FOR_MOVE = "3007";// 移机

	public static final String CHG_SERV_SPEC_ID_FOR_CHANGE_ACC_NBR = "3006";// 改号

	public static final String CHG_SERV_SPEC_ID_FOR_CHANGE_ACC_NBR_AND_ADD_SUB = "3053";// 改号加增减附属功能
	
	// 接口标识funcode

	public static final String FUNCODE_RECEIVE_SO = "receiveSo"; // 接收订单请求
	
	public static final String FUNCODE_IS_ALLOW_CANCEL_SO = "isAllowCancelSo"; // 撤单询问接口
	
	public static final String FUNCODE_IS_ALLOW_CHANGE_SO = "isAllowChangeSo"; // 改单询问接口
	
	public static final String FUNCODE_UN_LOCK_SO = "unLockSo"; // 定单解锁接口
	
	public static final String FUNCODE_RECEIVE_ROLL_SO = "receiveRollSo"; // 定单反向接口
	
	public static final String FUNCODE_RE_RECEIVE_SO = "reReceiveSo"; // 重做单接口
	
	public static final String FUNCODE_CHANGE_SO_BOOK = "changeSoBook"; // 预约修改接口
	
	public static final String FUNCODE_RECEIVE_CANCEL_SO_NOTIFY = "receiveCancelSoNotify"; // 撤单完成通知接口
	
	public static final String FUNCODE_SO_COMPLETE = "soComplete"; // 定单报竣接口
	
	public static final String FUNCODE_SO_EXPT_BACK = "soExptBack"; // 定单反向竣工接口
	
	public static final String FUNCODE_SO_SEND_BACK = "soSendBack"; // 定单退回营业接口
	
	public static final String FUNCODE_COML_PAY_NOTIFY = "complPayNotify"; // CRM收费通知接口
	
	public static final String FUNCODE_IS_PAY_CHARGE = "isPayCharge"; // IOM收费查询接口
	
	public static final String FUNCODE_CONNECT_TEST = "connectTest"; // 接口连通性测试接口
	
	public static final String FUNCODE_CHG_PASSWORD = "chgPass";// 同步密码接口
	
	public static final String FUNCODE_WORK_ORDER_RETURN = "workOrderReturn";// 施工调度回单接口
	
	public static final String FUNCODE_INCEPT_WO = "inceptWO";// 派单接口
	
	public static final String SCHEMA_FOR_SERVICE_ORDER = "ServiceOrder.xsd"; // 服务定单接口协议
	
	public static final String SCHEMA_FOR_SERVICE_ORDERS = "ServiceOrders.xsd"; // 批量服务定单接口协议
	
	public static final String SCHEMA_FOR_EXPT_SERVICE_ORDER = "ExptServiceOrder.xsd"; // 异常处理服务定单接口协议
	
	public static final String SCHEMA_FOR_SERVICE_ORDER_RETURN = "ServiceOrderReturn.xsd"; // 服务定单返回CRM接口协议
	
	public static final String SCHEMA_CATALOG = "schema\\"; // schema协议存放目录
	
	/** *********************************资源接口***************************************** */
	// 接口编码(_EID)
	public static final String EID_RES_SO_SYNC_SERV_REQ = "RES_SO_SYNC_SERV_REQ";// 定单同步请求

	public static final String EID_RES_SO_SYNC_SERV_RESP = "RES_SO_SYNC_SERV_RESP";// 定单同步返回

	public static final String EID_RES_DSGN_SERV_REQ = "RES_DSGN_SERV_REQ";// 资源配置方案设计请求

	public static final String EID_RES_DSGN_SERV_RESP = "RES_DSGN_SERV_RESP";// 资源配置方案设计返回

	public static final String EID_RES_QRY_SERV_REQ = "RES_QRY_SERV_REQ";// 查询资源服务请求

	public static final String EID_RES_ASGN_SERV_REQ = "RES_ASGN_SERV_REQ";// 分配资源服务请求
	
	public static final String EID_RES_ASGN_SSMODULE_REQ = "RES_ASGN_SSMODULE_REQ";//软交换模块核配
	
	public static final String EID_RES_ASGN_SSMODULE_RESP = "RES_ASGN_SSMODULE_RESP";//软交换模块核配返回

	public static final String EID_RES_CNL_SERV_REQ = "RES_CNL_SERV_REQ";// 撤除资源服务请求

	public static final String EID_RES_SO_REVERSE_REQ = "RES_SO_REVERSE_REQ";// 定单反向请求

	public static final String EID_RES_ASGN_SERV_RESP = "RES_ASGN_SERV_RESP";// 分配资源服务返回

	public static final String EID_RES_ARC_SERV_REQ = "RES_ARC_SERV_REQ";// 归档服务请求

	public static final String EID_RES_ARC_SERV_RESP = "RES_ARC_SERV_RESP";// 归档服务返回

	public static final String EID_RES_CHECK_REQ = "RES_CHECK_REQ";// 资源质检请求

	public static final String EID_RES_CHECK_RESP = "RES_CHECK_RESP";// 资源质检返回
	
	public static final String EID_RES_PRE_CHECK_REQ = "RES_PRE_CHECK_REQ";// 资源预判请求
	
	public static final String EID_RES_PRE_CANCEL_CHECK_REQ = "EID_RES_PRE_CANCEL_CHECK_REQ";// 资源预判请求撤销 add by 戎恒恩 20110311

	public static final String EID_RES_PRE_CHECK_RESP = "RES_PRE_CHECK_RESP";// 资源预判返回

	public static final String EID_RES_REPL_SERV_RESP = "RES_REPL_SERV_RESP";// 资源后补返回

	public static final String EID_RES_CTO_SO_REQ = "RES_CTO_SO_RESP";// 资源崔单请求

	public static final String EID_RES_CTO_SO_RESP = "RES_CTO_SO_RESP";// 崔单返回

	public static final String EID_RES_WAIT_REQ = "RES_WAIT_REQ";// 待装回单请求

	public static final String EID_RES_WAIT_RESP = "RES_WAIT_RESP";// 待装回单
	
	public static final String EID_RES_SO_ADDR_QUERY = "RES_SO_ADDR_QUERY"; // 根据标准地址查询包区信息
	
	public static final String EID_USED_RES_CHANGE = "USED_RES_CHANGE"; //在用用户资源变更，服务保障用；

	public static final String EID_USED_RES_QUERY = "SAS_QRY_SERV_REQ";//在用用户资源查询，服务保障用
	

	

	// 接口标识funcode

	public static final String FUNCODE_SO_SYNC_REQUEST = "soSyncRequest"; // 服务定单同步请求

	public static final String FUNCODE_SO_SYNC_RESPONSE = "soSyncResponse"; // 服务定单同步返回

	public static final String FUNCODE_RES_QUERY_REQUEST = "resQueryRequest"; // 资源服务查询请求

	public static final String FUNCODE_RES_ASSIGN_REQUEST = "resAssignRequest";// 资源服务配置请求

	public static final String FUNCODE_RES_ASSIGN_RESPONSE = "resAssignResponse"; // 资源服务配置反回
	
	public static final String FUNCODE_RES_ASGNSSMODULE_REQUEST = "resAsgnSsModuleRequest";//软交换模块核配请求

	public static final String FUNCODE_RES_ASSIGN_CANCEL_REQUEST = "resAssignCancelRequest";// 资源服务配置取消请求

	public static final String FUNCODE_RES_SO_REVERSE_REQUEST = "soReverseRequest";// 撤除资源请求

	public static final String FUNCODE_RES_DISIGN_REQUEST = "resDesignRequest"; // 资源服务设计请求

	public static final String FUNCODE_RES_DESIGN_RESPONSE = "resDesignResponse";// 资源服务设计返回

	public static final String FUNCODE_RES_ARCHIVE_REQUEST = "resArchiveRequest"; // 资源归档请求

	public static final String FUNCODE_RES_ARCHIVE_RESPONSE = "resArchiveResponse";// 资源归档返回

	public static final String FUNCODE_RES_CHECK_REQUEST = "resCheckRequest";// 资源质检请求

	public static final String FUNCODE_RES_CHECK_RESPONSE = "resCheckResponse"; // 资源质检反回

	public static final String FUNCODE_RES_PRE_CHECK_REQUEST = "resPreCheckRequest";// 资源预判请求
	
	public static final String FUNCODE_RES_CANCEL_PRE_CHECK_REQUEST = "resCanclePreCheckRequest";// 资源预判反向请求 add by 戎恒恩 20110311

	public static final String FUNCODE_RES_PRE_CHECK_RESPONSE = "resPreCheckResponse"; // 资源质检反回

	public static final String FUNCODE_RES_CTO_SO_REQUEST = "resCtoSoRequest";// 资源崔单请求
	
	public static final String FUNCODE_NAS_FEEDBACK = "nasFeedBack";//激活自动施工结果反馈 add by 戎恒恩 20110826
	
	public static final String FUNCODE_NAS_QUERY_FAILREASON = "queryFailReason";//激活查询可用异常原因 add by 戎恒恩 20110826

	public static final String FUNCODE_RES_CTO_SO_RESPONSE = "resCtoSoResponse"; // 崔单结果返回

	public static final String FUNCODE_RES_WAIT_REQ = "resWaitRequest";// 待装回单请求

	public static final String FUNCODE_RES_WAIT_RESP = "resWaitResponse";// 待装回单

	public static final String FUNCODE_RES_SO_ADDR_QRY_REQUEST = "soAddrQryRequest";// 根据标准地址查询维护区编码
	
	public static final String FUNCODE_RES_CHANGE_SAS_REQUEST = "usedResChange";	//在用用户资源变更，服务保障用
	
	public static final String FUNCODE_RES_QRY_SAS_REQUEST = "sasQueryRequest"; //在用用户资源查询，服务保障用

	public static final String FUNCODE_SAS_QRY_CRM_REQUEST = "sasQueryCRMRequest"; //客户资料查询，服务保障用
	
	public static final String FUNCODE_RES_RETURN_WO_RESPONSE = "resReturnWoResponse";//交资回单
	
	// 认证用户
	public static final String RES_SECURITY_PRINCIPAL = "admin"; // 用户

	// 认证密码
	public static final String RES_SECURITY_CREDENTIALS = "qazwsxedc";// 密码

	// 通讯方式
	public static final String EVENT_TYPE_FOR_SYNC = "SYNC"; // 同步

	public static final String EVENT_TYPE_FOR_ASYN = "ASYN"; // 异步

	//
	public static final String APPLICATION_SYSTEM = "SPS"; // 服务开通系统

	// 资源返回编码
	public static final String RES_RESULT_CODE_SUCCESS = "0"; // 调用成功

	public static final String RES_RESULT_CODE_FAIL = "-1"; // 调用成功

	// 施工调度系统返回编码
	public static final String WTS_RESULT_CODE_SUCCESS = "0"; // 调用成功

	public static final String WTS_RESULT_CODE_FAIL = "-1"; // 调用成功

	// 操作类型
	public static final String ACT_TYPE_ASSIGN = "A"; // 资源分配

	public static final String ACT_TYPE_QUERY = "Q"; // 资源查询

	public static final String ACT_TYPE_CANCEL = "C"; // 资源撤销

	public static final String ACT_TYPE_DESIGN = "D"; // 资源撤销

	// AZ标志转码
	public static final String AZ_FLAG_TYPE_A = "1";// 对应AZ_FLAG 的A端

	public static final String AZ_FLAG_TYPE_Z = "2";// 对应AZ_FLAG的Z端

	// AZ_FLAGAZ端标志
	public static final String AZ_FLAG_A = "A";// A端

	public static final String AZ_FLAG_Z = "Z";// Z端

	// 服务开通和资源交互环节定义

	public static final String STEP_ID_SO_SYNC = "SC0001";// 定单同步

	public static final String STEP_ID_QUERY_RES = "SC0002";// 资源查询
	
	public static final String STEP_ID_QUERY_RES_SAS = "SC2009";//资源查询(服务保障)

	public static final String STEP_ID_RES_DESIGN = "SC0003";// 服务设计

	public static final String STEP_ID_ASSIGN_LINE = "RA0001";// 配线

	public static final String STEP_ID_ASSIGN_NUM = "RA0002";// 配号

	public static final String STEP_ID_ASSIGN_PORT = "RA0003";// 配端口

	public static final String STEP_ID_LINE_CHECK = "RA0004";// 配线质检

	public static final String STEP_ID_RES_ARCHIVE = "SC0004";// 资源归档

	public static final String STEP_ID_ASSIGN_IP = "RA0015";// 配IP

	public static final String STEP_ID_ASSIGN_LINE_NUM = "RA0020";// 配专线号

	public static final String STEP_ID_ASSIGN_NUM_PORT = "RA0025";// 配数据端口

	public static final String STEP_ID_ASSIGN_LINE_PORT = "RA0030";// 配LAN端口

	public static final String STEP_ID_ASSIGN_MACHINRY_NUM = "RA0035";// 配机身码

	// 配号方式
	public static final String ASSIGN_NBR_TYPE_FOREGROUND = "0";// 前台选号
	public static final String ASSIGN_NBR_TYPE_FORBACKAUTOASSIGN = "1";// 后台自动配号

	// 号码类型

	public static final String ACC_NBR_PRPTY_DISTNBR = "1000006";// 逻辑号码区号

	public static final String ACC_NBR_PRPTY_ACCNBR = "1000001";// 逻辑号码

	public static final String ACC_NBR_PRPTY_PHYACCNBR = "1000000";// 物理号码

	public static final String ACC_NBR_PRPTY_PHYDISTNBR = "1000007";// 物理号码区号

	public static final String ACC_NBR_PRPTY_MDFPORT = "2000001";// 配线架端口

	public static final String ACC_NBR_PRPTY_MDFEPAIR = "3000026";// 配线架1横列端子
	
	public static final String ACC_NBR_PRPTY_MDFEPAIR2 = "3000031";//	配线架2横列端子

	public static final String ACC_NBR_PRPTY_MDFNAME = "3000025";// 配线架1名称
	
	public static final String ACC_NBR_PRPTY_MDFNAME2 = "3000030";// 配线架2名称

	public static final String ACC_NBR_PRPTY_MDFDPAIR = "3000027";// 配线架1直列端子1
	
	public static final String ACC_NBR_PRPTY_MDFDPAIR2 = "3000028";//配线架1直列端子2
	
	public static final String ACC_NBR_PRPTY_MDFDPAIR2_1 = "3000028";//配线架2直列端子1

	public static final String ACC_NBR_PRPTY_MDFDPAIR2_2 = "3000028";//配线架2直列端子2
	
	// MDF_E_I_PAIR
	public static final String ACC_NBR_PRPTY_MDF_E_I_PAIR = "2000014"; // 横列入端端子

	public static final String ACC_NBR_PRPTY_MDF_E_I_PAIR2 = "2000016"; // 主配线架2横列入端端子
	
	// MDF_E_O_PAIR
	public static final String ACC_NBR_PRPTY_MDF_E_O_PAIR = "2000015"; // 横列出端端子
	
	public static final String ACC_NBR_PRPTY_MDF_E_O_PAIR2 = "2000017"; // 主配线架2横列出端端子

	

	public static final String RES_PRPTY_IP_ADDR = "1000002";// IP地址

	public static final String RES_PRPTY_SUB_MASK = "1000020";// 子网掩码

	public static final String RES_PRPTY_GATEWAY = "4000016";// 网关

	public static final String SHARED_LINE_MAIN_PROD = "10000000";// 共线主产品

	public static final String ADSL_PROD_ID = "20001001";// adsl产品id

	public static final String ADSL_CHG_SERV_ID = "3001";// adsl装客户服务ID

	public static final String NUM_PORT_SERV_ID = "203";// 端口资源服务编码

	public static final String SYS_CONFIG_ASSIGN_WORK_AREA = "2000060";// 区域id

	public static final String SYS_CONFIG_ASSIGN_MDF_WORK_AREA = "3000046";// 配线架区域id
	
	public static final String SYS_CONFIG_ASSIGN_MDF_ID = "3000045";// 配线架id
	public static final String SYS_CONFIG_ASSIGN_EPON_WORK_AREA = "3000049";// EPON属性派单区域id


	// 资源配置

	public static final String RES_PRPTY_ID_SEQ = "RES_PRPTY_ID";// 资源属性id

	public static final String RES_PRPTY_VALUE_ID_SEQ = "RES_PRPTY_VALUE_ID";// 资源属性值id

	public static final String RES_PRPTY_TO_TYPE_ID_SEQ = "RES_PRPTY_TO_TYPE_ID";// 资源类型与资源属性关系id

	public static final String RES_PRPTY_TO_SERV_ID_SEQ = "RES_PRPTY_TO_SERV_ID";// 资源服务与资源属性关系id

	// 异常原因编码
	public static final String FAIL_REASON_RES_SYS_ERROR = "20000180";// 资源系统异常

	public static final String FAIL_REASON_SPS_SYS_ERROR = "20000195";// 服务开通系统异常

	public static final String FAIL_REASON_WTS_SYS_ERROR = "20000200"; // 施工调度系统异常

	/** *********************SQL查询状态参数*************************************** */

	public static final String STATUS_HIS = "_HIS";// 查询历史表

	public static final String STATUS_ARC = "_ARC";// 查询归档表

	/** ***********************操作类型id************************************************** */
	
	public static final String HANDLE_TYPE_CUSTOM_VISIT="509164"; //客户回访
	
	public static final String HANDLE_TYPE_WO_BUILD = "400000";// 工单生成400000

	public static final String HANDLE_TYPE_WO_FETCH = "400010";// 人工领单

	public static final String HANDLE_TYPE_WO_AUTO_DISPATCH = "400020";// 工单派发

	public static final String HANDLE_TYPE_WO_PRINT = "400030";// 工单打印

	public static final String HANDLE_TYPE_WO_SUCCESS_RET = "400040";// 成功回单

	public static final String HANDLE_TYPE_WO_FAIL_RET = "400050";// 失败回单

	public static final String HANDLE_TYPE_WO_RESEND = "400060";// 二次派发

	public static final String HANDLE_TYPE_WO_SENDTO = "400070";// 工单转派

	public static final String HANDLE_TYPE_WO_AUDIT = "400080";// 审核处理
	
	public static final String HANDLE_TYPE_WO_WAITRETURN = "500190";//等待回单 add by 戎 2011-817

	// public static final String HANDLE_TYPE_WO_RERET = "400090";// 重新回单

	public static final String HANDLE_TYPE_WO_CHG_WORK_MODE = "400100";// 施工方式变更

	public static final String HANDLE_TYPE_WO_SKIP = "400110";// 空白工单跳过

	public static final String HANDLE_TYPE_WO_EXCEPTION_RESEND = "400120";// 异常重发

	public static final String HANDLE_TYPE_WO_UNABLE = "400130";// 工单作废

	public static final String HANDLE_TYPE_WO_CC = "400150"; // 工单抄送

	public static final String HANDLE_TYPE_WO_MODIFY = "400131";// 工单修改

	public static final String HANDLE_TYPE_WO_DESIGN = "400160";// 施工设计结果

	// SO_HANDLE相关的HANDLE_TYPE
	public static final String HANDLE_TYPE_SO_RETURN = "400170";

	public static final String HANDLE_TYPE_SO_RETURN_INFO = "退回原岗";
	public static final String HANDLE_TYPE_SO_PASS = "400180";

	public static final String HANDLE_TYPE_SO_PASS_INFO = "审核通过";
	public static final String HANDLE_TYPE_SO_WAIT = "400160";

	public static final String HANDLE_TYPE_SO_WAIT_INFO = "等待审核";
	public static final String HANDLE_TYPE_SO_BASE_MOD = "400190";
	public static final String HANDLE_TYPE_SO_BASE_MOD_INFO = "定单修改";
	public static final String HANDLE_TYPE_SO_BASE_ADD = "400150";
	public static final String HANDLE_TYPE_SO_BASE_ADD_INFO = "定单录入";
	
	public static final String HANDLE_TYPE_SO_PRIORITY_ADJUST = "220280";
	public static final String HANDLE_TYPE_SO_PRIORITY_ADJUST_INFO = "定单优先级调整";
	
	public static final String HANDLE_TYPE_SO_MANAGE = "220281";
	public static final String HANDLE_TYPE_SO_MANAGE_INFO = "定单督办";

	public static final String HANDLE_TYPE_SO_PARSE = "500000";// 定单解析

	public static final String HANDLE_TYPE_SO_PARSE_INFO = "定单解析";//

	public static final String HANDLE_TYPE_SO_MATCH = "500010";// 定单匹配

	public static final String HANDLE_TYPE_SO_MATCH_INFO = "定单匹配";

	public static final String HANDLE_TYPE_SO_START_PROC = "500020";// 定单启动流程

	public static final String HANDLE_TYPE_SO_START_PROC_INFO = "定单启动流程";//

	public static final String HANDLE_TYPE_SO_CHOOSE_PROC = "500030";// 定单选择流程

	public static final String HANDLE_TYPE_SO_CHOOSE_PROC_INFO = "定单选择流程";

	public static final String HANDLE_TYPE_SO_COLLAB_WAIT = "500040";// 定单协同等待

	public static final String HANDLE_TYPE_SO_COLLAB_WAIT_INFO = "定单协同等待";

	public static final String HANDLE_TYPE_SO_PROC_FINISH = "500050";// 定单施工完成

	public static final String HANDLE_TYPE_SO_PROC_FINISH_INFO = "定单施工完成";

	public static final String HANDLE_TYPE_SO_RES_ASSIGN = "500060";// 定单资源配置返回更新数据

	public static final String HANDLE_TYPE_SO_RES_ASSIGN_INFO = "定单资源配置返回更新数据";

	public static final String HANDLE_TYPE_SO_RETURN_CRM = "500070";// 定单退回营业

	public static final String HANDLE_TYPE_SO_RETURN_CRM_INFO = "定单退回营业";

	public static final String HANDLE_TYPE_SO_PROD_RETURN = "500080";// 产品属性回填

	public static final String HANDLE_TYPE_SO_PROD_RETURN_INFO = "产品属性回填";

	public static final String HANDLE_TYPE_SO_LOCK = "500090";// 定单锁定

	public static final String HANDLE_TYPE_SO_LOCK_INFO = "定单锁定";

	public static final String HANDLE_TYPE_SO_UNLOCK = "500100";// 定单解锁

	public static final String HANDLE_TYPE_SO_UNLOCK_INFO = "定单解锁";

	public static final String HANDLE_TYPE_SO_REVERSE = "500110";// 接收反向定单

	public static final String HANDLE_TYPE_SO_REVERSE_INFO = "接收反向定单";

	public static final String HANDLE_TYPE_SO_REVERSE_FINISH = "500120";// 定单反向完成

	public static final String HANDLE_TYPE_SO_REVERSE_FINISH_INFO = "定单反向完成";

	public static final String HANDLE_TYPE_SO_PAY_FINISH = "500130";// 定单收费完成

	public static final String HANDLE_TYPE_SO_PAY_FINISH_INFO = "定单收费完成";

	public static final String HANDLE_TYPE_SO_REDO_NOTIFY = "500140";// 定单重做通知

	public static final String HANDLE_TYPE_SO_REDO_NOTIFY_INFO = "定单重做通知";

	public static final String HANDLE_TYPE_SO_CANCLE_FINISH_NOTIFY = "500150";// 定单撤单完成通知

	public static final String HANDLE_TYPE_SO_CANCLE_FINISH_NOTIFY_INFO = "定单撤单完成通知";

	public static final String HANDLE_TYPE_SO_BOOK = "400140";

	public static final String HANDLE_TYPE_SO_BOOK_INFO = "定单预约";
	
	public static final String HANDLE_TYPE_SO_BOOK_SENDDATE_TO_CRM = "定单预约返CRM时间片";

	public static final String HANDLE_TYPE_RES_RELEASE_STEP = "220260";// 失败回单资源释放

	public static final String HANDLE_TYPE_RES_RELEASE_STEP_INFO = "失败回单资源释放";

	public static final String HANDLE_TYPE_SO_MODIFY = "502000";// 接收反向定单

	public static final String HANDLE_TYPE_SO_MODIFY_INFO = "定单修改";

	public static final String HANDLE_TYPE_EXP_FORWARD_INVOKE = "500200";// 异常流程前调

	public static final String HANDLE_TYPE_EXP_FORWARD_INVOKE_INFO = "异常流程前调";

	public static final String HANDLE_TYPE_EXP_BACKWARD_INVOKE = "500201";// 异常流程后调

	public static final String HANDLE_TYPE_EXP_BACKWARD_INVOKE_INFO = "异常流程后调";

	public static final String HANDLE_TYPE_BW_WO_RESEND = "500202";// 工单重发

	public static final String HANDLE_TYPE_BW_WO_BACK = "500203";// 强行回单

	public static final String HANDLE_TYPE_BW_WO_REVERSE = "500204";// 强行反向
	
	public static final String HANDLE_TYPE_CHANGE_SO_BOOK = "400141";//  修改预约时间  for联创 
	
	public static final String HANDLE_TYPE_CHANGE_SO_BOOK_INFO = "CRM修改预约";
	
	public static final String HANDLE_TYPE_PRESSTYP_PRESS = "400200"; //派发催单
	
	public static final String HANDLE_TYPE_PRESSTYP_MSG = "400201";  //派发短信
	
	public static final String HANDLE_TYPE_PRESSTYP_OTHER = "400202"; //其它方式
	
	public static final String HANDLE_TYPE_SPEC_PRPTY = "220300"; // 属性回填

	public static final String HANDLE_TYPE_SPEC_PRPTY_INFO = "属性回填";// 属性回填

	// --------------旧

	public static final String HANDLE_TYPE_SENDTO = "220172"; // 施工处理/工单转派

	public static final String HANDLE_TYPE_SUCCESS_RETURN = "220160";// 回单正常回单

	public static final String HANDLE_TYPE_FAIL_RETURN = "220161"; // 回单失败回单

	public static final String HANDLE_TYPE_SEND_TO = "220171"; // 领单二次派发

	public static final String HANDLE_TYPE_BIDE_TO_COMFIRM = "220254";// 转待装确认

	public static final String HANDLE_TYPE_BIDE_COMFIRM = "220255";// 装待装
	
	public static final String HANDLE_TYPE_DELAY_FIX = "220259";// 缓装转入
	
	public static final String HANDLE_TYPE_DELAY_FIX_OUT = "220290";// 缓装转出

	public static final String HANDLE_TYPE_EXPLORE = "220343"; // 转外勘

	public static final String HANDLE_TYPE_FETCH_NOT_PRINT = "220173"; // 只领单未打印

	public static final String HANDLE_TYPE_FETCH_PRINTED = "220183"; // 领单并打印

	public static final String HANDLE_TYPE_TO_MANUAL = "220200";// 统一工单调用转人工处理

	public static final String HANDLE_TYPE_NAI_FAIL = "220201";// 统一工单调用返回失败

	public static final String HANDLE_TYPE_NAI_FETCH = "220202";// 统一工单接口调用成功

	public static final String HANDLE_TYPE_EXP_RESEND = "220192"; // 异常工单异常重发

	public static final String HANDLE_TYPE_INVOKE_FAIL = "220196"; // 自动接口调用失败

	public static final String HANDLE_TYPE_DUE = "220170"; // 预约未到期工单预约 ???????

	public static final String HANDLE_TYPE_SYNC_REQUEST = "220330"; // 定单同步请求处理

	public static final String HANDLE_TYPE_SYNC_RESPONSE = "220331"; // 定单同步请求返回

	public static final String HANDLE_TYPE_QUERY_REQUEST = "220332"; // 资源查询请求

	public static final String HANDLE_TYPE_ASSIGN_REQUEST = "220333"; // 资源配置请求

	public static final String HANDLE_TYPE_CANCEL_ASSIGN_REQUEST = "220334"; // 撤销资源配置请求

	public static final String HANDLE_TYPE_ASSIGN_RESPONSE = "220335"; // 资源配置返回

	public static final String HANDLE_TYPE_CHECK_REQUEST = "222336"; // 资源审核请求

	public static final String HANDLE_TYPE_CHECK_RESPONSE = "220337"; // 资源审核返回

	public static final String HANDLE_TYPE_REVERSE_REQUEST = "220338"; // 定单反向请求

	public static final String HANDLE_TYPE_ARCHIVE_REQUEST = "220339"; // 资源归档请求

	public static final String HANDLE_TYPE_REPLENISH_RESPONSE = "220340"; // 候补资源返回给服务开通系统

	public static final String HANDLE_TYPE_REPLENISH_RESPONSE_INFO = "候补资源返回服务开通"; // 候补资源返回给服务开通系统

	public static final String HANDLE_TYPE_CTO_SO_REQUEST = "220341"; // 资源割接崔单请求

	public static final String HANDLE_TYPE_CTO_SO_RESPONSE = "220342"; // 资源割接崔单返回

	public static final String HANDLE_TYPE_WAIT_SO_RESPONSE = "220344"; // 候补资源返回给服务开通系统

	public static final String HANDLE_TYPE_RE_RECEIVE_SO = "220320";// CRM重新发单

	public static final String HANDLE_TYPE_NAS_REBUILD = "220000";// 网元接口异常监控异常重发

	public static final String HANDLE_TYPE_NAS_DOSUCESS = "220001";// 网元接口异常监控强行成功

	public static final String HANDLE_TYPE_BS_REBUILD = "220003";// 欠费停复异常工单异常重发

	public static final String HANDLE_TYPE_BS_REMOVE = "220002";// 清除欠费停复数据
	
	public static final String HANDLE_TYPE_BSWOHIS_REBUILD = "220004";// 停复状态跟踪异常重发

	public static final String HANDLE_TYPE_BSWOHIS_DOSUCESS = "220005";// 停复状态跟踪强制成功

	public static final String SO_PARSE_SUCCESS = "220230";// 定单解析成功

	public static final String SO_PARSE_SUCCESS_INFO = "定单解析成功";// 定单解析成功处理信息

	public static final String SO_PARSE_SUCCESS_REASON_INFO = "SO: SO_LOCK_STS ->N,SO_STS ->A";// 定单解析成功具体内容

	public static final String SO_MATCH_FAILED = "220231";// 定单匹配失败

	public static final String SO_MATCH_FAILED_INFO = "定单匹配失败";// 定单匹配失败处理信息

	public static final String SO_MATCH_FAILED_REASON_INFO = "SO: SO_STS A->M";// 定单匹配失败具体内容

	public static final String WAITING_CHOICE_PROC = "220232";// 等待选择流程

	public static final String WAITING_CHOICE_PROC_INFO = "等待选择流程";// 等待选择流程处理信息

	public static final String WAITING_CHOICE_PROC_REASON_INFO = "SO: SO_STS A->W";// 等待选择流程具体内容

	public static final String WAITING_START_PROC = "220233";// 等待启动流程

	public static final String WAITING_START_PROC_INFO = "等待启动流程";// 等待启动流程处理信息

	public static final String WAITING_START_PROC_REASON_INFO = "SO: SO_STS A->D";// 等待启动流程具体内容

	public static final String PROC_START_SUCCESS = "220234";// 流程启动成功

	public static final String PROC_START_SUCCESS_INFO = "流程启动成功";// 流程启动成功处理信息

	public static final String PROC_START_SUCCESS_REASON_INFO = "SO: SO_STS A->P";// 流程启动成功具体内容

	public static final String PROC_START_FAILED = "220235";// 流程启动失败

	public static final String PROC_START_FAILED_INFO = "流程启动失败";// 流程启动失败处理信息

	public static final String PROC_START_FAILED_REASON_INFO = "SO: SO_STS A->E";// 流程启动失败具体内容

	public static final String PROC_INST_CREATE_SUCCESS = "220250";// 流程协同规则实例创建成功

	public static final String PROC_INST_CREATE_SUCCESS_INFO = "流程协同规则实例创建成功";// 流程协同规则实例创建成功处理信息

	public static final String PROC_INST_CREATE_SUCCESS_REASON_INFO = "流程协同规则实例创建成功";// 流程协同规则实例创建成功具体内容

	public static final String PROC_INST_CREATE_FAILED = "220251";// 流程协同规则实例创建失败

	public static final String PROC_INST_CREATE_FAILED_INFO = "流程协同规则实例创建失败";// 流程协同规则实例创建失败处理信息

	public static final String PROC_INST_CREATE_FAILED_REASON_INFO = "流程协同规则实例创建失败";// 流程协同规则实例创建失败具体内容

	public static final String STEP_INST_CREATE_SUCCESS = "220252";// 环节协同规则实例创建成功

	public static final String STEP_INST_CREATE_SUCCESS_INFO = "环节协同规则实例创建成功";// 环节协同规则实例创建成功处理信息

	public static final String STEP_INST_CREATE_SUCCESS_REASON_INFO = "环节协同规则实例创建成功";// 环节协同规则实例创建成功具体内容

	public static final String STEP_INST_CREATE_FAILED = "220253";// 环节协同规则实例创建失败

	public static final String STEP_ISNT_CREATE_FAILED_INFO = "环节协同规则实例创建失败";// 环节协同规则实例创建失败处理信息

	public static final String STEP_INST_CREATE_FAILED_REASON_INFO = "环节协同规则实例创建失败";// 环节协同规则实例创建失败具体内容

	public static final String CHANGE_SO_LOCK_STS_ID = "220270";// 改单锁定

	public static final String CHANGE_SO_LOCK_STS_INFO = "改单锁定";

	public static final String CANCLE_SO_LOCK_STS_ID = "220271";// 220271

	public static final String CANCLE_SO_LOCK_STS_INFO = "撤单锁定";

	public static final String UN_SO_LOCK_STS_ID = "220272";// 220272

	public static final String UN_SO_LOCK_STS_INFO = "解锁";

	public static final String RE_RECEIVE_SO_ID = "220273";// 接收重发单

	public static final String RE_RECEIVE_SO_INFO = "接收重发单";

	public static final String DELAY_SO_STS_ID = "220274";// 220274 缓装

	public static final String DELAY_SO_STS_INFO = "缓装";

	public static final String RESUME_SO_STS_ID = "220275";// 220275 复装

	public static final String RESUME_SO_STS_INFO = "复装";

	public static final String COMPL_PAY_NOTIFY_ID = "220276";// 220276 收费完成

	public static final String COMPL_PAY_NOTIFY_INFO = "收费完成";

	public static final String SO_MATCH_PROCESS_FAIL = "0"; // 定单匹配处理失败

	public static final String SO_MATCH_PROCESS_SUCCESS = "1";// 定单匹配处理成功

	public static final String SO_PROCESS_START_FAIL = "2";// 定单启动失败

	public static final String HANDLE_TYPE_FAILRETURN_NEED_AUDIT = "220256"; // 需要审核

	public static final String HANDLE_TYPE_FAILRETURN_FAIL_STAY = "220257"; // 失败滞留

	public static final String HANDLE_TYPE_FAILRETURN_WAIT_WITHDRAW = "220258"; // 等待撤改单

	public static final String HANDLE_TYPE_ADD_PRINT_COUNT = "220278"; // 增加工单打印次数

	public static final String HANDLE_TYPE_CHECK_AUDIT_NOT_PASS = "220221";// 审核不通过，退回原岗

	// --------------------------系统context环境
	public static final String CONTEXT_WFS = "wfs";// context-config.xml中工作流环境配置

	public static final String CONTEXT_RMS = "rms";// context-config.xml中资源环境配置

	public static final String CONTEXT_NAI = "nai";// context-config.xml中资源环境配置

	// ----------------------------------Timer 接口－－－－－－－－－－－－－－－－－－－
	// TIMER和服务开通的失败原因定义
	public static final String FAIL_CODE_ONE = "-1";// 调用资源前发生异常

	public static final String FAIL_CODE_TWO = "-2";// 调用资源后发生异常

	public static final String FAIL_CODE_ZERO = "0";// 调用资源后发生异常

	// 施工环节页面
	public static final String STEP_PLUG_FLAG_YES = "0"; // 游离标志 0

	/** *******************工单打印参数*********************************** */

	public static final float PAGELENGTH = 297;// 页面长度 单位mm

	public static final float PAGEWIDTH = 210;// 页面宽度 单位mm

	// 打印组合元素的调用方式
	public static final String CALLTYPE_PROCEDURE = "P"; // 调用方式是存储过程

	public static final String CALLTYPE_JAVA = "J";// 调用方式是JAVA

	public static final String CALLTYPE_SQL = "S"; // 动态SQL

	public static final String CALLTYPE_METHOD = "N"; // 代码实现，不调用方法

	public static final String PROCEDURE_PRINT_RES_PRPTY = "PRINT.PRINT_RES_PRPTY"; // 取资源信息时所调用的存储过程名称
	
	public static final String PROCEDURE_PRINT_RELA_RES_AP = "PRINT.PRINT_RELA_RES_AP"; // 取关联单资源信息时所调用的存储过程名称

	public static final String PROCEDURE_PRINT_MAIN_PROD_PRPTY = "PRINT.PRINT_MAIN_PROD_PRPTY"; // 取主产品信息时所调用的存储过程名称
	
	public static final String PROCEDURE_PRINT_MATCH_TEMPLATEID = "PRINT.PRINT_MATCH_TEMPLATEID"; // 合单时精确匹配模板ID

	// public static final int RECORD_COUNT_PRE_PAGE = 10; // 施工派单交接单每页显示的记录数

	public static final String PRINT_RECORD_COUNT_PRE_PAGE = "70005"; // 施工派单交接单每页显示的记录数

	public static final String SPEC_TEMPLATE_ID = "100054"; // 施工派单交接单模版id

	// public static final int SPACE_BETWEEN = 80; // 施工派单交接单的两条记录的间距

	public static final String PRINT_SPACE_BETWEEN = "70004"; // 施工派单交接单的每条工单的跨度

	public static final float SPACE_LEFT = 10; // 施工派单交接单出报日期的左边距

	public static final int SPACE_TOP = 44; // 施工派单交接单TITLE的顶边距

	public static final float SPACE_RIGHT = 740; // 施工派单交接单右边距

	public static final String PRINT_WORK_TYPE = "70001"; // 可打印交接单的工区类型的SYS_CONFIG_ID

	public static final String PRINT_HANDLE_OVER_TEMPLATE_ID = "70002"; // 交接单的template_id的SYS_CONFIG_ID

	public static final String PRINT_DEPARTMENT = "[WO100120]"; // 交接单的部门的sys_item_id

	public static final int SIMPLE_PRINT_COUNT_PREPAGE = 2; // 测量工位简单工单每页打印工单数

	public static final float SIMPLE_PRINT_SPAN = 370; // 测量工位简单工单 每个工单所占高度

	public static final String PRINT_AREA_ID = "[CI100120]"; // 测量工位简单工单

	// 标题部分的服务区的SYS_ITEM_ID

	public static final String PRINT_WORK_AREA_ID = "[WO100120]"; // 测量工位简单工单

	// 标题部分的工作区的SYS_ITEM_ID

	public static final float SIMPLE_SPACE_LEFT = 500; // 测量简单工单打印日期的左边距

	public static final float SIMPLE_SPACE_TOP = 20; // 测量简单工单打印日期的顶边距

	public static final String PRINT_SIMPLE_WORK_TYPE = "70006"; // 可打印简单工单的工区类型

	public static final String PRINT_SYS_ITEM_NO_FLAG_N = "N"; // sys_item表中元素如果table_name里配置的表没有no_flag字段,则应配置no_flag='N'

	public static final String PRINT_FROM_M = "M"; // 模板上动态元素来源于主工单

	public static final String PRINT_FROM_S1 = "S1"; // 模板上动态元素来源于从工单1

	public static final String PRINT_FROM_S2 = "S2"; // 模板上动态元素来源于从工单2
	
	public static final String PRINT_FROM_N = "N"; // 模板上动态元素无主从

	public static final String PRINT_MERGFLAG_Y = "Y"; // WO.MERG_FLAG = Y 已合并

	public static final String PRINT_MERGFLAG_N = "N"; // WO.MERG_FLAG = N 未合并

	public static final String PRINT_MERGFLAG_W = "W"; // WO.MERG_FLAG = W 无须合并
	
	public static final String PRINT_PAPER_SIZE = "70007"; // 打印纸张大小
	
	public static final String SIMPLE_DATE_SPACE_LEFT = "70008"; // 简单打印时打印出报日期的左边距
	
	public static final String SIMPLE_DATE_SPACE_TOP = "70009"; // 简单打印时打印出报日期的顶边距
	

	/** *******************退单审核*********************************** */
	// 异常前调
	public static final String MSG_SPS_WFS_EVENT_PROC_EXP_DISPATCH = "PROC_EXP_DISPATCH"; // 异常流程人工调度(向前调度)

	// 异常后调
	public static final String MSG_SPS_WFS_EVENT_PROC_EXP_DISPATCH_FORWARD = "PROC_EXP_DISPATCH_FORWARD"; // 异常流程人工调度(向后调度)

	public static final String IS_WO_CHECK = "71006"; // 可以进行工单审核功能的启用关闭开关

	public static final String WORKFLOWDOEVENT_RET_CODE = "0"; // WfApiDOM().workFlowDoEvent()的返回值

	// 调工作流返回的值 0调用成功,失败抛出异常.

	// 调归档方法返回值
	public static final String CUR_TO_ARC_FAIL = "N";

	public static final String CUR_TO_ARC_SUCCESS = "Y";

	// 集成CRM
	public static final String IS_CENTER_LOCAL_NET_Y = "Y";

	public static final String MAIN_FLAG_N = "N";

	public static final String MAIN_FLAG_Y = "Y";

	public static final String BUSINESS_TYPE_A = "A";

	/* S 面向通信服务的客户服务类型 */
	public static final String BUSINESS_TYPE_COMM_SERV_SPEC = "S";

	/* 附加通信服务的服务动作 */
	public static final String ACT_ADD_ADD_PRODUCT = "3061"; // 附加通信服务增动作ID

	public static final String STS_HISTORY = "P";

	public static final String STS_IN_USE = "A";

	public static final String SEQ_SPEC_PRPTY_INC_ID = "SPEC_PRPTY_INC_ID";// 服务属性

	public static final String BUSI_MATCH_ID = "BUSI_MATCH_ID";// 业务命令场景事件编号

	public static final String ORDER_MATCH_ID = "ORDER_MATCH_ID";// 命令匹配结果编号

	/* 产品配置中属性值配置 */
	public static final String SPEC_PRPTY_VALUE_ID_SEQ = "SPEC_PRPTY_VALUE_ID";// 属性值配置编号

	public static final String CONFIG_ID_CRM_FILL_BUILDING_URL = "88890";

	/* 接口状态值 */
	public static final String SPJK_TABLE_STS_SEND_FAIL = "E";

	public static final String SPJK_TABLE_STS_WAIT_SEND = "D";

	public static final String SPJK_TABLE_STS_SEND_COMP = "P";

	public static final String SPJK_TABLE_STS_SUCCESS = "C";

	/* UBI */
	public static final int UBI_START_OFFSET = 0; // start field

	public static final int UBI_START_LENGTH = 4;

	public static final String UBI_START_VALUE = "`SF`";

	public static final int UBI_VERSION_OFFSET = 4;// version field

	public static final int UBI_VERSION_LENGTH = 4;

	public static final String UBI_VERSION_VALUE = "1.00";

	public static final String UBI_VERSION_NAME = "UBI_VERSION";

	public static final int UBI_LENGTH_OFFSET = 8;// length field

	public static final int UBI_LENGTH_LENGTH = 4;

	public static final String UBI_LENGTH_NAME = "UBI_LENGTH";

	public static final int UBI_SEQUENCE_OFFSET = 12;// sequence field

	public static final int UBI_SEQUENCE_LENGTH = 4;

	public static final String UBI_SEQUENCE_NAME = "UBI_SEQUENCE";

	public static final int UBI_TYPE_OFFSET = 16;// type field

	public static final int UBI_TYPE_LENGTH = 4;

	public static final String UBI_TYPE_NAME = "UBI_TYPE";

	public static final String UBI_INFO_NAME = "UBI_INFO";

	public static final String UBI_TYPE_LGIN = "LGIN";

	public static final String UBI_TYPE_LGOT = "LGOT";

	public static final String UBI_TYPE_INFO = "INFO";

	public static final String UBI_TYPE_TEST = "TEST";

	public static final int UBI_INFO_OFFSET = 20;// infomation field

	public static final String UBI_INFO_OK = "OK";

	public static final String UBI_INFO_ERR = "ERROR";

	public static final String UBI_INFO_TEST = "HBTEST";

	public static final int UBI_END_LENGTH = 4;// end field

	public static final String UBI_END_VALUE = "`EF`";

	public static final int UBI_HEAD_LEN = 20; // head length

	public byte[] head = new byte[UBI_HEAD_LEN];// head bytes

	/* 统一工单服务服务地址 */
	public static final String SOCKET_SERVER_CONFIG_ID = "90000";

	public static final String SYS_ITEM_ID_SEQ = "SYS_ITEM_ID";// 系统元素编码

	/* 许可标志 USER_DATA_RANGE */
	public static final String ALLOW_FLAG_READ = "R";

	public static final String ALLOW_FLAG_ADD = "A";

	public static final String ALLOW_FLAG_UPDATE = "U";

	public static final String ALLOW_FLAG_DELETE = "D";

	public static final String ALL0W_FLAG_QUERY = "Q";
	// 表示不加权限校验
	public static final String ALL0W_FLAG_NOLIMIT = "Y";

	/* 区域类型，对应USER_DATA_RANGE.RANGE_TYPE_ID */
	public static final String AREA_TYPE_LOCAL_NET = "L";

	public static final String AREA_TYPE_AREA = "A";

	public static final String BACKFILL_FLAG_SUCCESS = "Y";

	public static final String BACKFILL_FLAG_FAIL = "F";

	/* 定单服务实例组 SO_SI_GROUP */
	// public static final String GROUP_TYPE_ID_DEFAULT = "S";
	public static final String SO_SI_GROUP_MEMBER_STS_IN_USE = "A";

	/* 定单费用信息 SO_CHARGE */
	public static final String SO_CHARGE_LOCAL_NET_ID_DEFAULT = "0";

	public static final String SO_CHARGE_SO_NBR_DEFAULT = "0";

	public static final String SO_CHARGE_NO_FLAG_DEFAULT = "A";// 新

	public static final String SO_CHARGE_CHARGE_DEFAULT = "0";

	public static final String SO_CHARGE_PAY_FLAG_DEFAULT = "Y";// 已付费

	public static final String SO_CHARGE_HOME_BILL_FLAG_DEFAULT = "N";// 上门不收

	public static final String SO_CHARGE_STS_DEFAULT = "A";// 有效

	public static final String SO_CHARGE_PAY_FLAG_NO = "N";// 未付费

	/* 是否允许释放资源的标志WO，STEP_FAIL_REASON */
	public static final String RES_RELEASE_FALG_YES = "Y";

	public static final String RES_RELEASE_FALG_NO = "N";

	/* 生成工单方式 */
	public static final String REAL_TIME_STATUS_NN = "NN"; // 非实时正常
	// 前一位代表实时标志，后一位区分正常异常

	public static final String REAL_TIME_STATUS_YN = "YN"; // 实时正常

	public static final String REAL_TIME_STATUS_YE = "YE"; // 实时异常

	/* 群组是否发生变化的标志 */
	public static final String GROUP_CHANGE_FLAG_FOR_YES = "Y"; // 群组发生变化

	public static final String GROUP_CHANGE_FLAG_FOR_NO = "N"; // 群组未发生变化

	/* 新旧群组标志 */
	public static final String GROUP_NOFLAG = "AP";// 值未有任何含义

	/* 旧群组解析流程选择标志 */
	public static final String OLD_GROUP_PARSE_FLAG = "S";

	/* 非局向工区配置 */
	public static final String DEFAULT_LOCAL_NET_ID = "0"; // 默认本地网ID

	public static final String ALL_LOCAL_NET_ID = "0"; // 所有本地网

	public static final String SYS_LOCAL_NET_ID = "999"; // 系统本地网

	public static final int ARC_DEAL_COUNT = 3;

	public static final String ALL_AREA_ID = "0"; // 所有服务区

	public static final String ALL_EXCH_ID = "0"; // 所有局向

	public static final String ALL_SERV_DEPT_ID = "0"; // 所有营维中心

	/* 日志操作内容 */
	public static final String ACTION_LOG_SPEC_PRPTY = "属性:";

	public static final String ACTION_LOG_SPEC_PRPTY_VALUE = "属性值:";

	// 系统元素表FOR_SCENE的定义
	public static final String SYS_ITEM_FOR_SCENE_WO_PRINT = "WO_PRINT"; // 工单打印
	public static final String SYS_ITEM_FOR_SCENE_SO_MATCH = "SO_MATCH"; // 定单模板匹配
	public static final String SYS_ITEM_FOR_SCENE_WO_PRPTY = "WO_PRPTY"; // 工单属性取值

	/* 属性修改记录 */
	public static final String PRPTY_CHANGE_LOG_ID_SEQ = "PRPTY_CHANGE_LOG_ID";// 主键值

	public static final String LOG_TYPE_FILL_BACK = "F";// 日志类型为属性回填
	public static final String RETURN_TYPE_NORMAL = "C";// 回单类型为正常

	// 主工单标志
	public static final String MAIN_WO_Y = "Y";
	public static final String MAIN_WO_N = "N";
	// 施工调度请求接口常量定义WW

	// 属性取值以来标志
	public static final String DEP_FLAG_Y = "Y";
	public static final String DEP_FLAG_N = "N";

	public static final String SYS_CONFIG_ORG_DEPT = "71003";// 区域管理按纽显示控制
	public static final String SYS_CONFIG_STAFF = "71004";
	
	public static final String SYS_CONFIG_MAINT_AREA = "71024";//包区管理按钮显示控制

	public static final String EXCH_ID_SEQ = "EXCH_ID"; // 局向ID

	public static final String SYS_CONFIG_STEP_POWER_CONFIG = "71007";// 环节权重配置开关

	public static final String ACTION_LOG_STEP_DESIGN_MATCH = "环节设计匹配要素:";

	public static final String ACTION_LOG_STEP_DESIGN = "环节设计:";

	/* 带宽专题涉及的各种设计类型 */
	public static final String BW_DESIGN_TYPE_FREE_FLOW = "F";// 自由流设计

	public static final String BW_DESIGN_TYPE_WO_FACTORS = "W";// 工单因素设计

	public static final String BW_DESIGN_TYPE_RES_ASSIGN = "R";// 资源配置方案设计

	public static final String BW_DESIGN_TYPE_KEYVALUE = "K";// 多分支路由设计
	
	public static final String BW_DESIGN_TYPE_FAULT_RELATION = "D";// 障碍关联方案设计
	
	public static final String SA_DESIGN_TYPE_MAN_DISPATCH = "A";// 服务保障自动路由设计
	
	public static final String SA_CUST_NAME ="无客户信息"; //服务保障客户虚拟名称
	public static final String SA_CUST_Id="1000001";//服务保障客户虚拟ID


	/* 目标对象类型 */
	public static final String BW_OBJ_TYPE_NAME_STEP = "环节";

	public static final String BW_OBJ_TYPE_NAME_RES_SERV = "资源服务";

	/* 设计系统 */
	public static final String BW_SYSTEM_SPS = "SPS";

	public static final String BW_SYSTEM_RMS = "RMS";

	/* 目标对象类型 */
	public static final String BW_OBJ_TYPE_STEP = "S";

	public static final String BW_OBJ_TYPE_KEYVALUE = "K";

	public static final String BW_OBJ_TYPE_RES_SERV = "R";

	/* 前置对象类型 */
	public static final String BW_PRE_OBJ_TYPE_STEP = "S";

	public static final String BW_PRE_OBJ_TYPE_NODE = "N";

	/* 设计状态 */
	public static final String BW_DESIGN_STS_DESIGN = "D";// 插入状态

	// 暂不考虑自由流设计结果的状态变迁为"C"确认状态！
	public static final String BW_DESIGN_STS_CONFIRM = BW_DESIGN_STS_DESIGN;//"C";// 预留

	public static final String BW_DESIGN_STS_EXECUTE = "E";// 重构完

	/* 流程设计标识 */
	public static final String BW_FREE_FLOW_FLAG_YES = "Y";

	public static final String BW_FREE_FLOW_FLAG_NO = "N";

	/* 子流程(sub_flow_list)活动参数编码取值 */

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_LIMIT_PRE_ALARM_CODE = "PROC_NODE_LIMIT_PRE_ALARM";// 预警时限

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_LIMIT_ALARM_CODE = "PROC_NODE_LIMIT_ALARM";// 告警时限

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_LIMIT_HASTEN_CODE = "PROC_NODE_LIMIT_HASTEN";// 催单时限

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_LIMIT_METRIC = "PROC_NODE_LIMIT_METRIC";// 时限单位

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_LIMIT_CREATE_STAFF_ID = "PROC_NODE_LIMIT_CREATE_STAFF_ID";// 时限创建人员

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_LIMIT_CREATE_DATE = "PROC_NODE_LIMIT_CREATE_DATE";// 时限创建时间

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_LOCAL_NET_ID = "LOCAL_NET_ID";

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_WORK_AREA_ID = "WORK_AREA_ID";
	
	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_AREA_ID = "AREA_ID";

	public static final String SUB_FLOW_PARAMLIST_PROC_NODE_WO_STAFF_ID = "WO_STAFF_ID";

	
	/* 异常流程前后调标志 */
	public static final String BW_EXP_FORWARD_INVOKE = "0";// 异常前调

	public static final String BW_EXP_BACKWARD_INVOKE = "1";// 异常后调

	/* 路由信息查询 */

	public static final String ROUTER_NODE_DESC = "NODE_DESC";

	public static final String ROUTER_DESIGN_NODE_INSTANCE_ID = "DESIGN_NODE_INSTANCE_ID";

	public static final String ROUTER_KEY_VALUE = "KEY_VALUE";
	
	public static final String ROUTER_EXPRESS_NODE_INSTANCE_ID = "EXPRESS_NODE_INSTANCE_ID";

	/* 设计工单的派单状态 */
	public static final String WO_DESIGN_DETAIL_DESIGN_DETAIL_STS_READY = "A";// 就绪

	public static final String WO_DESIGN_DETAIL_DESIGN_DETAIL_STS_COMPLATE = "C";// 完成

	/** 接口全景配置相关变量 */
	public static final String conn_Y = "Y"; // 连通正常

	public static final String conn_N = "N"; // 连通失败

	public static final String SYS_CONFIG_NAS_SOCKET = "80000";// NAS接口用于建立SOCKET连接的IP和端口

	public static final String IM_CRM = "1";// 监控CRM接口inter_id

	public static final String IM_RMS = "2";// 监控RMS接口inter_id

	public static final String IM_NAS = "3";// 监控NAS接口inter_id

	public static final String IM_BILL = "4";// 监控NAS接口inter_id

	public static final String SYS_IMMONITOR_CONFIG_ID = "90007";// 监控NAS接口inter_id
	
	public static final String SYS_STAFF_CONFIG_ID ="110021";//巡检人员分布刷新周期设置id
	
	public static final String APPLI_PROCESS_STATUS_CONFIG_ID = "90008";// 后台监控视图 接口inter_id

	/* 参数对象类型(工单因素管理添加) */
	public static final String PARAMETER_OBJ_TYPE_PROC = "P";

	public static final String PARAMETER_OBJ_TYPE_NODE = "N";

	public static final String PARAMETER_OBJ_TYPE_ACTIVITY = "A";

	public static final String BIND_REL_COLUMN = "relColumn";

	// 完善基础数据管理功能 added by yangkai 2009-04-24
	public static final String CERT_TYPE_ID_SEQ = "CERT_TYPE_ID";// 证件类型序列

	public static final String CUST_LEVEL_ID_SEQ = "CUST_LEVEL_ID";// 客户级别序列

	public static final String CUST_VOCA_ID_SEQ = "CUST_VOCA_ID";// 客户行业序列

	public static final String CUST_CAT_ID_SEQ = "CUST_CAT_ID";// 客户类型序列

	/** 定单快捷查询 开关配置id */
	public static final String SYS_CONFIG_QUICK_QUERY = "71008";
	
	/** 定单快捷查询 查询数目配置id */
	public static final String SYS_CONFIG_QUICK_QUERY_NUMBER = "71009";

	// 查看并行工单状态开关
	public static final String SYS_CONFIG_PARALLEL_WO_RUN_STS_FLAG = "70011";

	// 优先级规则配置
	public static final String SO_PRIORITY_ID_SEQ = "SO_PRIORITY_ID";// 证件类型序列

	// inter_service_order表状态
	public static final String INTER_SERVICE_ORDER_STS_A = "A";

	public static final String INTER_SERVICE_ORDER_STS_B = "B";

	public static final String INTER_SERVICE_ORDER_STS_C = "C";

	public static final String INTER_SERVICE_ORDER_STS_D = "D";

	public static final String INTER_SO_EXCEPTION_STS_C = "C";

	// added by yangkai 2009-06-10
	public static final String GROUP_SPEC_SPS_ID_SEQ = "GROUP_SPEC_SPS_ID";// 群组规格序列

	public static final String BUSI_RULE_ID_SEQ = "BUSI_RULE_ID";// 业务规则序列

	// added by yangkai 2009-06-11
	public static final String ACT_TYPE_MAP_ID_SEQ = "ACT_TYPE_MAP_ID";// 动作映射序列

	// added by yangkai 2009-06-15
	public static final String STEP_SPEC_SERV_ID_SEQ = "STEP_SPEC_SERV_ID";// 环节专业服务序列

	// add by liyaquan 2009-06-10
	// no_flag转换
	public static final String BIZ_EXCH_TYPE_AP = "NOFLAG_AP";
	public static final String BIZ_EXCH_TYPE_P = "NOFLAG_P";
	public static final String BIZ_PRPTY_TYPE_FLAG = "NO_FLAG";
	public static final String BIZ_TYPE_FLAG = "CHG_SERV_SPEC";

	// so表记录状态为再用
	public static final String SO_STS_A = "A";
	
	/* 重构方式*/
	public static final String REBUILD_MODE_MANUAL = "M"; // 按人工设计结果重构
	
	public static final String REBUILD_MODE_AUTO = "A"; // 按环节权重重构
	
	/* 设计类型*/
	public static final String RESOURCE_DESIGN_RESPONSE_DESIGN_TYPE_RES = "0";// 计资源服务

	public static final String RESOURCE_DESIGN_RESPONSE_DESIGN_TYPE_RES_SORT = "1";// 资源服务和顺序设计

	//GROUP_TYPE.TO_OBJ_TYPE 	 ADD BY YIYI
	public static final String GROUP_TYPE_TO_OBJ_TYPE_WO = "WO";
	
	public static final String GROUP_TYPE_TO_OBJ_TYPE_SO = "SO";
	
	public static final String WO_PRESS_TYPE_PRE_ALARM = "P";
	public static final String WO_PRESS_TYPE_ALARM = "A";

	/**SLA模板配置,SLA目录*/
	public static final String SLA_TEMPLATE_ID_SEQ = "SLA_TEMPLATE_ID"; //SLA模板元素
	
	public static final String SLA_ELEMENT_ID_SEQ = "SLA_ELEMENT_ID"; //SLA模板元素
	
	public static final String SYS_CONFIG_SURVEY_WORKTYPEID = "40040";
	
	/*-------------流程干预快速查询级别--------------*/
	public static final String WF_ADJUST_QUERY_RECENT_START = "1";// 最近启动

	public static final String WF_ADJUST_QUERY_RECENT_ACTIVITY = "2";// 最近活动

	public static final String WF_ADJUST_QUERY_RECENT_LOCK = "3";// 最近锁定

	public static final String WF_ADJUST_QUERY_ANCIENT_ACTIVITY = "4";// 最久未活动
	
	/*-------------通知对象类型&发送者对象类型--------------*/
	
	public static final String SENDER_TYPE_SYSTEM="S";//通知者类型为系统
	public static final String SENDER_TYPE_PERSON="P";//通知者类型为个人
	
	public static final String OBJECT_TYPE_PROVINCE = "P"; //省
	
	public static final String OBJECT_TYPE_LOCAL_NET = "L"; //本地网
	
	public static final String OBJECT_TYPE_AREA = "A"; //服务区
	
	public static final String OBJECT_TYPE_WORK_AREA = "W"; //工区
	
	public static final String OBJECT_TYPE_STAFF = "S"; //员工
	
	public static final String OBJECT_TYPE_SYSTEM="S";
	public static final String FROM_OBJECT_TYPE_SYSTEM = "S"; //发送对象类型  系统
	public static final String FROM_OBJECT_TYPE_PROVINCE = "D"; //发送对象类型 省
	public static final String FROM_OBJECT_TYPE_LOCAL_NET  = "L"; //发送对象类型 本地网
	public static final String FROM_OBJECT_TYPE_AREA = "A"; //发送对象类型 服务区
	public static final String FROM_OBJECT_TYPE_WORK_AREA = "W"; //发送对象类型
	public static final String FROM_OBJECT_TYPE_PERSON = "P"; //发送对象类型
	
	public static final String TO_OBJECT_TYPE_SYSTEM = "S"; //接收对象类型  系统
	public static final String TO_OBJECT_TYPE_PROVINCE = "D"; //接收对象类型 省
	public static final String TO_OBJECT_TYPE_LOCAL_NET  = "L"; //接收对象类型 本地网
	public static final String TO_OBJECT_TYPE_AREA = "A"; //接收对象类型 服务区
	public static final String TO_OBJECT_TYPE_WORK_AREA = "W"; //接收对象类型
	public static final String TO_OBJECT_TYPE_PERSON = "P"; //接收对象类型
	
	//MSG_MATCH.OBJECT_TYPE对象类型
	public static final String OBJECT_TYPE_STEP = "STEP";//STEP 施工环节
	
	public static final String OBJECT_TYPE_SPSV = "SPSV";//SPSV  专业服务
	
	public static final String OBJECT_TYPE_CKPT = "CKPT";//CKPT  监管指标
	/*检查对象配置省一级别*/
	public static final String CHECK_RULE_OBJECT_ID = "10001";  
	//通过OSS统一门户登陆之后默认转向应用
	public static final String SSO_DEFAULT_APP ="Y";
	
	public static final String FAULT_WORK_TYPE_ID="215006"; //障碍查修工区类型id
	
	
	
	//MSG_MATCH.EVENT_TYPE 事件类型
	public static final String EVENT_TYPE_S = "S"; // 派单、指派、转派通知
	
	public static final String EVENT_TYPE_D = "D"; // 工单生成通知
	
	public static final String EVENT_TYPE_P = "P"; // 领单通知
	
	public static final String EVENT_TYPE_U = "U"; // 催单通知
	
	public static final String EVENT_TYPE_C = "C"; // 退单通知
	
	public static final String EVENT_TYPE_B = "B"; // 预约通知
	
	public static final String EVENT_TYPE_R = "R"; // 回单通知
	
	public static final String EVENT_TYPE_T = "T"; // 转派通知
		
	public static final String EVENT_TYPE_A = "A"; // 告警通知
	
	public static final String EVENT_TYPE_W = "W"; // 预警通知
	
	public static final String EVENT_TYPE_H = "H"; // 催修通知
	
	public static final String EVENT_TYPE_N = "N"; // 重派通知
	
	public static final String EVENT_TYPE_M = "M"; // MOS消息推送
	
	public static final String EVENT_TYPE_S_INFO = "派单、指派、转派通知"; // 派单、指派、转派通知
	
	public static final String EVENT_TYPE_R_INFO = "回单通知"; // 回单通知
	
	public static final String EVENT_TYPE_P_INFO = "领单通知"; // 领单通知
	
	public static final String EVENT_TYPE_U_INFO = "催单通知"; // 催单通知
	
	public static final String EVENT_TYPE_C_INFO = "退单通知"; // 退单通知
	
	public static final String EVENT_TYPE_B_INFO = "预约通知"; // 预约通知
	
	public static final String EVENT_TYPE_T_INFO = "转派通知";
	
	public static final String EVENT_TYPE_A_INFO = "预警告警通知"; // 预警告警通知
	
	public static final String MSG_EVENT_TYPE_SO = "SO"; //业务定单
	
	public static final String MSG_EVENT_TYPE_WO = "WO"; //施工工单
	
	public static final String MSG_EVENT_TYPE_TO = "TO"; //施工任务
	
	public static final String MSG_EVENT_TYPE_AL = "AL"; //预警告警
	
	public static final String MSG_EVENT_TYPE_MOS = "F";//MOS 测试通知
	
	public static final String MSG_EVENT_TYPE_MOS_PUSH = "MOS";//MOS消息推送

	public static final String MSG_MATCH_ID = "9476";//MOS 测试通知
	
	public static final String MSG_SURVEY_ID = "9480";//MOS 回访通知
	//MSG.STS
	
	public static final String MSG_STS_D = "D"; //等待发送
	
	public static final String MSG_STS_P = "P"; //已经发送
	
	public static final String MSG_STS_E = "E"; //发送失败
	
	public static final String MSG_STS_C = "C"; //发送成功
	
	public static final String MSG_STS_R = "R"; //已经作废
	
	//MSG.MSG_TYPE
	public static final String MSG_TYPE_PARSE_BILLING = "BP";  //inter_msg_center.msg_type
	
	public static final String MSG_TYPE_PARSE_SO = "SP"; //inter_msg_center.msg_type
	
	public static final String MSG_TYPE_MATCH_BILLING = "BM"; //so_msg_center.msg_type
	
	public static final String MSG_TYPE_MATCH_SO = "SM"; //so_msg_center.msg_type
	
	public static final String MSG_TYPE_MATCH_SA = "SA";//so_msg_center.msg_type
	
	public static final String MSG_TYPE_PARSE_CO = "CP"; //解析客户订单（湖北版本）
 
	
/*--------------------------施工处理----------------------------*/
	public static final String LOCAL_NET_ID_ALL = "0"; //所有本地网
	
	public static final String LOCAL_NET_ID_SYS = "999"; //系统本地网
 
	
/*------------------查询类别 赵丹@2009-7-15--------------------*/
	
	public static final String SIMPLE_QUERY = "1"; //简单查询
	
	public static final String ADVANCE_QUERY = "2"; //高级查询
	
	public static final String QUICK_QUERY="3"; //快捷查询 
	
	
	//added by yangkai 2009-8-6
	public static final String IM4CRMService="IM4CRMService"; //Web Service名为IM4CRMService
	public static final String IM4BillingService="IM4BillingService"; //Web Service名为IOM4BillService
	public static final String CrmService="CrmService"; //Web Service名为CrmService
	public static final String BillService="BillService"; //计费提供的WebService名
	public static final String WtsService = "ForSps6InterFace"; // 施工调度提供的WebService名
	public static final String DEFAULT_VALUE="-1";//向批量定单解析消息表中插入默认值-1
	public static final String MSG_TYPE_BP="BP";//向批量定单解析消息表中插入消息类型为默认值BP
	public static final String SYS_CONFIG_WSURL_BILLING ="110001"; //sys_config 计费提供的Webservice的URL
	public static final String SYS_CONFIG_WSURL_CRM ="110000"; //sys_config CRM提供的Webservice的URL
	public static final String SYS_CONFIG_WSURL_RMS ="110002"; //sys_config RMS提供的Webservice的URL
	public static final String SYS_CONFIG_WSURL_LOCAL ="110003"; //sys_config 本系统对外提供的Webservice的URL
	public static final String SYS_CONFIG_WSURL_WTS ="110004"; //sys_config WTS提供的Webservice的URL
	public static final String RMS_SERVICE = "Sps2RmsService"; // 资源提供的webservice名字
	// add by peiyy
	public static final String SYS_CONFIG_WSURL_SMS = "110018"; // 联创CRM短信webserviceURL
	public static final String SMS_SERVICE = "SmsService"; // 联创短信接口webservice
    public static final String FUNCODE_WORK_AREA_AND_STAFF = "queryWorkAreaStaff";//
    public static final String FUNCODE_UPDATE_CHANGE_ORDER = "updateChangeOrder";//
    public static final String FUNCODE_SEND_TO_WITH_AUDIT = "sendToWithAudit";//MOS转派前审核

	// add by yanxin
	public static final String SYS_CONFIG_WSURL_BLOCK = "110022"; // 驻地网新建接口webserviceURL
	public static final String BlockService="BlockService"; //驻地网新建接口WebService
	public static final String SYS_CONFIG_BLOCK_PROD_PRPTY = "225050"; // 驻地网接口主产品属性
	public static final String SYS_CONFIG_CREATE_BLOCK_PROD_PRPTY_VALUE = "225051"; // 驻地网新建接口主产品属性值
	public static final String SYS_CONFIG_EXTEND_BLOCK_PROD_PRPTY_VALUE = "225052"; // 驻地网扩容接口主产品属性值
	
	//added by yangkai 2009-8-17
	public static final String DATASET_LEVEL="0";// 默认WF_WORK_DAY_TIME级别为"全局"
	public static final String WORK_DAY_TIME_ID_SEQ="WORK_DAY_TIME_ID";// 新增工作日序列
	public static final String WORK_DAY_TIME_LIST_ID_SEQ="WORK_DAY_TIME_LIST_ID";// 新增工作日序列
	public static final String AreaTypeName="本地网";// 默认为本地网
	public static final String STATE="0";// 状态为在用
	
	public static final String PRESS_TYPE_PRESS ="P";// 生成催单
	public static final String PRESS_TYPE_MSG ="S";// 生成短信
	public static final String PRESS_TYPE_OTHER ="O";// 其它
	
	/*--------------------------------流程建模Flex-------------------开始------------------------*/
	
	public static final String PROCESS_TYPE_ADJUST = "WORKFLOW_ADJUST";// 流程调整
	public static final String PROCESS_TYPE_DESIGN = "WORKFLOW_DESIGN";// 流程设计
	
	public static final String CONECT_SYMBOL = "-"; // 连接符
	public static final String FIRST_NODE_CODE = "01"; // 第一个节点标识
	public static final String DESIGN_BYMAN = "Y";  //人工设计活动
	public static final String WF_PROC_NODE_SEQ = "WF_PROC_NODE_ID";
	public static final String WF_PROC_INST_SEQ = "PROC_INST_ID";
	public static final String WF_PROC_NODE_INST_SEQ = "PROC_NODE_INST_ID";
	public static final String WF_PROC_NODE_PARAM_SEQ = "WF_PROC_NODE_PARAM_ID";
	public static final String WF_EXP_FLOW_INST_SEQ = "WF_EXP_FLOW_INST_ID";
	public static final String WF_EXP_FLOW_SEQ = "EXP_FLOW_ID";
	public static final String WF_EXP_FLOW_ACTIVITY_REGION_SEQ = "EXP_FLOW_ACT_REGION_ID";
	public static final String WF_EXP_FLOW_REGION_INST_SEQ = "WF_EXP_FLOW_REGION_INST_ID";
	public static final String WF_EXP_FLOW_REGION_SEQ = "EXP_FLOW_REGION_ID";
	public static final String WF_EXP_FLOW_NODE_SEQ = "WF_EXP_FLOW_NODE_SEQ";
	public static final String WF_EXP_FLOW_NODE_INST_SEQ = "WF_EXP_FLOW_NOD_INST_SEQ";
	public static final String ORIGIN_METHOD_PROCESS_IMPORT = "PROCESS_IMPORT"; //入口是从流程导入方法打开流程XML
	/*--------------------节点类型-------------------------------------*/
	static public final String NODE_TYPE_PROC_NO = "1"; // 活动节点

	static public final String NODE_TYPE_EXPRESS = "2"; // 条件节点

	static public final String NODE_TYPE_VIR = "3"; // 伪节点
	
	static public final String NODE_TYPE_CPN = "4"; //子流程节点
	
	public static final String LOOP_NODE = "1"; // 环节节点
	public static final String FALSE_NODE = "3"; // 伪节点
	public static final String CONDITION_NODE = "2"; // 条件节点
	public static final String LIMIT_USE_FLAG_A = "A";  //A:使用活动时限(wf_activity_limit)
	public static final String LIMIT_USE_FLAG_N = "N"; //N:使用节点时限(wf_proc_node_limit)
	public static final String LIMIT_USE_FLAG_I = "I"; // I:使用节点实例时限(wf_proc_node_inst_limit)
	public static final String LIMIT_STATE_NO_USE = "1";  //1 :时限表的STATE字段;1 作废 ;0 在用
	public static final String LIMIT_STATE_USE = "0";  //0 :时限表的STATE字段;1 作废 ;0 在用
	public static final String CONECT_BY_AND = "2"; // 并行连接
	public static final String CONECT_BY_SEQ = "1"; // 串行连接
	public static final String PARAMTYPE_FIXED = "1";

	public static final String PARAMTYPE_IF_ELSE = "2";
	public static final String PARAMTYPE_EXPRESS = "4";
	
	public static final String PARAMTYPE_MAN = "3";
	
	// 属性面板显示的wf_proc的配置区域类型
	public static final String LOCALNET = "本地网"; // 对应L
	public static final String AREA = "服务区"; // 对应A
	public static final String WORKAREA = "工作区"; // 对应W	
	
	// 节点类型
	public static final String NODE_TYPE_ACTIVITY = "ACTIVITY";
	public static final String NODE_TYPE_ROUTE = "ROUTE";
	public static final String NODE_TYPE_JOIN = "JOIN";
	public static final String NODE_TYPE_START = "START";
	public static final String NODE_TYPE_END = "END";
	public static final String ROUTE_TYPE_AND = "AND";
	public static final String ROUTE_TYPE_CONDITION = "CONDITION";
	public static final String ROUTE_TYPE_CASE = "CASE";
	public static final String ROUTE_TYPE_MAN = "MAN";
	public static final String NODE_TYPE_SUBPROC ="SUBPROC";
	
//	 ExpressType
	public static final String EXPRESS_TYPE_PROC = "1";

	public static final String EXPRESS_TYPE_NODE = "2";

	public static final String EXPRESS_TYPE_TIME= "3";
	//area_type_desc
	public static final String AREA_TYPE_LOCALNETDESC = "本地网"; // 对应L
	public static final String AREA_TYPE_AREADESC = "服务区"; // 对应A
	public static final String AREA_TYPE_WORKAREADESC = "工作区"; // 对应W
	
    /*----------------------------------流程树根节点id-------------------------------------*/
    public static final String ROOT_CATALOG_ID = "0";	
    
    /*----------------------------------流程标识-------------------------------------*/
    public static final String PROC_TYPE_SUB_PROC = "SUBPROC";
    public static final String PROC_TYPE_PROC_SAVE_AS = "PROC_SAVE_AS";
    public static final String PROC_TYPE_PROC_MOVE_TO = "PROC_MOVE_TO";
    public static final String PROC_TYPE_PROC_PRIV = "PROC_PRIV";
    
	/*****************************流程级别*******************************/
	public static final String PROC_CAT_CHILD = "C";// 子流程
	public static final String PROC_CAT_PARENT = "P";// 父流程
	
	/** *************************流程版本****************************** */
	
	public static final String PROC_VERSION = "PROC_VERSION";
	public static final String PROC_VERSION_SIGN = "V";//版本标识
	public static final String PROC_DEFAULT_VERSION = "V1.0";
	
	public static final String ZERO_CODE = "0"; // 零
	public static final String CUST_NAME_FOR_NON = "未知";
	
	public static final String EXTENDED_ATTRIBUTE_ROUTE_PARAM_ID = "ROUTE_PARAM_ID";
    
    public static final String EXTENDED_ATTRIBUTE_REFACTOR = "REFACTOR";
    
    public static final String EXTENDED_ATTRIBUTE_CASE_VALUE_TYPE = "CASE_VALUE_TYPE";
    
    public static final String EXTENDED_ATTRIBUTE_PARAM_TYPE = "PARAM_TYPE";
    
	/*----------------------------------条件路由对应的分支键值的类型------------------------------------*/
    public static final String CASE_OPTION_CASE_VALUE = "CASE_VALUE";

	public static final String CASE_OPTION_EXPRESS = "EXPRESS";
	
	public static final String DEFAULT_CASE_VALUE = "SYS_DEFAULT";
	
	public static final String DEFAULT_CASE_VALUE_NAME = "其他";
	
	public static final String ORIGIN_LINE = "from_line";// 异常线异常流程配置
	public static final String ORIGIN_NODE = "from_node";// 节点异常流程配置
	
	// 异常管理配置所用常量
	public static final String COMBOVALUE_SOMENODE = "指定的某些节点";
	public static final String COMBOVALUE_ONENODE = "指定到某一节点";
	public static final String COMBOVALUE_ISAPPOINT_YES = "指定";
	public static final String COMBOVALUE_ISAPPOINT_NO = "未指定";
	public static final String COMBOVALUE_REDO = "重做";
	public static final String COMBOVALUE_REDOAFTERCANCEL = "撤销后重做";

	public static final String COMBOVALUE_PROC = "流程";
	public static final String COMBOVALUE_WAIT = "外堪、待装";
	public static final String COMBOVALUE_RETURN = "回退到发起系统";
	public static final String COMBOVALUE_BLOCK = "滞留当前环节";

	public static final String SETOBJ_TITLE = "设置对象";
	
	/***************************活动时限权限标识*******************************/
	
	public static final String PERMISSIONS_FLAG_YES = "Y";

	public static final String PERMISSIONS_FLAG_NO = "N";	
	
	// 时限列
	public static final String LIMIT_ACTIVITY_VO="WfActivityLimit";
	public static final String LIMIT_NODE_VO="WfProcNodeLimit";
	public static final String LIMIT_NODE_INSTANCE_VO="WfProcNodeInstanceLimit";
	public static final String LIMIT_PROC_VO="WfProcLimit";
	public static final String LIMIT_PROC_INSTANCE_VO="WfProcInstanceLimit";
	
	
	public static final String LIMIT_AREA_TYPE = "区域类型";
	public static final String LIMIT_AREA = "区域标示";
	public static final String LIMIT_METRICUNIT = "计量单位";
	public static final String LIMIT_METRICVALUE = "告警时限";
	public static final String LIMIT_HASTENVALUE = "催单时限";
	public static final String LIMIT_PREVALUE = "预警时限";
	public static final String LIMIT_EXPRESS = "条件表达式";
	public static final String LIMIT_STATE = "状态";
	public static final String LIMIT_REMARK = "备注";
	
	public static final String OBJECT_TYPE_ACTIVITY = "0"; //WF_ACTIVITY_LIMIT.OBJECT_TYPE  0 活动
	public static final String OBJECT_TYPE_ACTIVITY_REGION = "1"; //WF_ACTIVITY_LIMIT.OBJECT_TYPE 1 活动区域
	
	/*--------------------------------流程建模SEQ--------------------------*/
	
	public static final String WF_PROC_LIMIT_SEQ = "PROC_LIMIT_ID";
	public static final String WF_PROC_INST_LIMIT_SEQ = "WF_PROC_INST_LIMIT_ID";
	public static final String WF_ACTIVITY_LIMIT_SEQ = "ACTIVITY_LIMIT_ID";
	public static final String WF_PROC_NODE_INST_LIMIT_SEQ = "WF_PROC_NODE_INST_LIMIT_ID";
	public static final String WF_PROC_NODE_LIMIT_SEQ = "WF_PROC_NODE_LIMIT_ID";
	
	/*--------------------------------流程时限 --------------------------*/
	
	public static final String LIMIT_PROC = "流程时限配置";
	public static final String LIMIT_PROC_INSTANCE = "流程实例时限配置";
	public static final String LIMIT_PROC_NODE = "节点时限配置";
	public static final String LIMIT_ACTIVITY = "活动时限配置";
	public static final String LIMIT_NODE_INSTANCE = "节点实例时限配置";
	
	/*--------------------------------流程建模Flex --------------------------------结束------------------*/
	
	//added by yangkai 2009-8-24
	public static final String INTER_SERVICE_ORDERS_ID_SEQ = "INTER_SERVICE_ORDERS_ID";// 批量服务定单接口表序列
	public static final String PWD_FLAG = "N";//加密算法选择，如果是N则使用MD5算法，否则为联创提供的算法
	
	//add by shilei 2009-9-4
	/*--------------------------------校验规则----------------------------------------*/
	public static final String PARSE_CONFIG_CONFIG_TYPE_BASIC = "0";// 基础类型
	
	public static final String PARSE_CONFIG_CONFIG_TYPE = "1";// 非基础类型
	
	public static final String CHK_RULE_OPEN_FLAG_OPEN = "O";// 启用

	public static final String CHK_RULE_OPEN_FLAG_CLOSE = "C";// 禁用
	
	public static final String CHK_RULE_BASE_FLAG_YES = "Y";// 基础校验

	public static final String CHK_RULE_BASE_FLAG_NO = "N";// 非基础校验
	
	public static final String CHK_RULE_RESULT_SUCCESS = "0";// 校验成功

	public static final String CHK_RULE_RESULT_FAILED = "1";// 校验失败
	
	public static final String MATCH_FACTOR_SO_CHECK = "1";// 特殊解析匹配因素（电信业务）
	
	public static final String PARSE_CONFIG_XML_PATH = "XML_PATH";// Properties-key

	public static final String SERVICE_ORDER_DOCUMENT = "SERVICE_ORDER_DOCUMENT";// Properties-key
	
	public static final String CHK_RULE_FACTOR_TYPE = "FACTOR_TYPE";// Properties-key
	
	public static final String BASE_CHK_RULE_PROD_MAP = "PROD_MAP";// Properties-key
	
	public static final String BASE_CHK_RULE_CHG_SERV_SPEC_MAP = "CHG_SERV_SPEC_MAP";// Properties-key
	
	public static final String BASE_CHK_RULE_BUSINESS_MAP = "BUSINESS_MAP";// Properties-key
	
	public static final String SRC_PARAM_XML = "SRC_PARAM_XML"; //Properties-key
	
	public static final String FOR_SCENE_SA_CHECK = "SA_CHECK"; //保障单校验
		
	public static final String FOR_SCENE_SO_CHECK = "SO_CHECK";// 定单校验

	public static final String FOR_SCENE_RS_ASSIGN = "RS_ASSIGN";// 资源分配

	public static final String FOR_SCENE_RS_QUERY = "RS_QUERY";// 资源查询
	
	public static final String MATCH_FACTOR_BASIC = "0";// 基础类匹配因素
	
	public static final String MATCH_FACTOR = "1";
	
	public static final String BUSI_RULE_CALL_TYPE_JAVA_CLASS = "J";// Java Class

	public static final String BUSI_RULE_CALL_TYPE_PROCEDURE = "P";// 存储过程

	public static final String BUSI_RULE_CALL_TYPE_SQL = "S";// 动态SQL
	
	public static final String PARAMETER_SEPARATOR = "/";// 参数分隔符
	
	public static final String LIST_CHK_RULE_BASIC = "0";// 基础校验

	public static final String LIST_CHK_RULE_UN_RELA = "1";// 全局校验

	public static final String LIST_CHK_RULE_RELA = "2";// 依赖校验
	
	public static final String CACHE_MAP_KEY_BUSINESS = "1";// 缓存Key值序列

	public static final String CACHE_MAP_KEY_BUSI_RULE = "2";// 缓存Key值序列

	public static final String CACHE_MAP_KEY_CHK_RULE = "3";// 缓存Key值序列

	public static final String CACHE_MAP_KEY_PARSE_CONFIG = "4";// 缓存Key值序列
	
	public static final String CACHE_MAP_KEY_PROD = "5";// 缓存Key值序列
	
	public static final String CACHE_MAP_KEY_CHG_SERV_SPEC = "6";// 缓存Key值序列

	public static final String FACTOR_TYPE_PROD = "P";// 解析匹配（产品）

	public static final String FACTOR_TYPE_CHG_SERV_SPEC = "C";// 解析匹配（客户服务）

	public static final String FACTOR_TYPE_BUSINESS = "B";// 解析匹配（电信业务）

	public static final String FACTOR_TYPE_BASIC = "0";// 解析匹配（基础校验）
	
	public static final String CHK_RULE_PLACEHOLDER = "PLACEHOLDER";// 占位符
	
	public static final String XML_PATH_FOR_CHG_SERV_SPEC= "//SoInfo/ChgServSpecId"; // 客户服务在serviceOrder中的位置，即客户服务的XML_PATH值
	
	public static final String SYS_CONFIG_IS_CHECK_MOD_CHG_SERV_SPEC_IDS= "110014"; // 互改类客户服务编码(以逗号隔开的字符串)
	
	public static final String STRING_FOR_PROD_INFO= "ProdInfo"; // 字符串
	
	public static final String STRING_FOR_FROM_PROD_INFO= "FromProdInfo"; // 字符串
	
	/********************************************	SAS START	*****************************************/
	
	public static final String SYS_CONFIG_DEFAULT_PROD_CAT_ID = "215005";
	
	public static final String SYS_CONFIG_AUTO_VOICE_OVERTIME_ID = "215008"; //自动语音超时监控时限(单位：秒)
	
	public static final String SYS_CONFIG_FIRST_QUERY_RMS_ID = "215010";//故障受理查询CRM及RMS顺序开关,Y，先查询RMS，N先查询CRM
	
	public static final String FAULT_TYPE_ID = "10000";
	
	public static final String FAULT_CAT_GENERAL = "G";//普通障碍
	
	public static final String FAULT_CAT_PUBLIC = "P";//公共障碍
	
	public static final String FAULT_CAT_BATH = "S";//大面积障碍
	
	public static final String FAULT_RELATION_GS = "GS";//普通障碍关联到大面积障碍
	
	public static final String FAULT_RELATION_GG = "GG";//普通障碍关联到普通障碍
	
	public static final String FAULT_RELATION_GP = "GP";//普通障碍关联到公共障碍
	
	public static final String FAULT_APPLY_CHG_SERV_SPEC_ID = "2000000";//服务保障默认客户服务
	
	public static final String MANSEND_CHG_SERV_SPEC_ID = "2000100";//服务保障人工派单客户服务
	
	public static final String HANDLE_TYPE_CONTINUE = "C";//故障关联关系处理方式
	
	public static final String FAULT_HANDLE_TYPE_DESIGN = "design";//故障分析方式:设计
	
	public static final String FAULT_HANDLE_TYPE_RELATE = "relate";//故障分析方式:关联
	
	public static final String STEP_ID_FAULT_APPLY = "SG2000";//故障受理，不对应流程环节
	
	public static final String STEP_ID_FAULT_RELATE = "SG2010";//故障关联环节
	
	public static final String STEP_ID_FAULT_DESIGN = "SG2002";//故障方案设计
	
	public static final String MOST_ACC_NBR = "0";//大面积障碍虚拟业务号码
	
	public static final String SYS_CONFIG_WSURL_112 = "110005"; // sys_config
	
	// 定单种类
	public static final String SO_SO_CAT_SPS = "1"; // 1 营业受理类开通

	public static final String SO_SO_CAT_SGS = "2"; // 2 服务保障类
	
	public static final String SO_WORK_AREA_Id= "470101"; //保障系统大面积障碍虚拟工区ID
	
	public static final String SO_WORK_AREA_NAME= "内蒙工区名称"; //保障系统大面积障碍虚拟工区名称
	
	// seq
	public static final String SEQ_SO_FAULT_ID = "SO_FAULT_ID"; // 定单故障信息表主键
	
	public static final String SO_FAULT_COMPLAINT_ID_SEQ = "SO_FAULT_COMPLAINT_ID";//服务保障投诉记录表主键
	
	public static final String SO_FAULT_RESULT_ID_SEQ = "SO_FAULT_RESULT_ID";//障碍查修结果反馈接口表主键

	public static final String SEQ_SO_ACC_NBR_ID = "SO_ACC_NBR_ID"; // 定单业务号码表主键
	
	public static final String HANDLE_TYPE_FAULT_APPLY_INFO = "故障受理";   //故障受理
	
	public static final String HANDLE_TYPE_RE_APPLY_INFO = "重复申告";       //重复申告
	
	public static final String HANDLE_TYPE_MODIFY_INFO = "故障变更";       //故障变更
	
	public static final String HANDLE_TYPE_FAULT_APPLY = "600000";   //故障受理
	
	public static final String HANDLE_TYPE_RE_APPLY = "600002";       //重复申告
	
	public static final String HANDLE_TYPE_MODIFY = "600003";       //故障变更
	
	public static final String HANDLE_TYPE_DIRECT_ARC = "600001";     //直接归档

	
	public static final String HANDLE_TYPE_LINE_TEST = "600000"; //线路测试
	public static final String HANDLE_TYPE_PORT_RESET = "600001"; //端口复位
	public static final String HANDLE_TYPE_PORT_QUERY = "600002"; //端口测试
	
	public static final String FAULT_PHENOM_ID_SEQ = "FAULT_PHENOM_ID";// 故障现象序列
	
	public static final String FAULT_NBR_CLASS = "1";// 号码等级

	public static final String FAULT_LEVEL_ID_SEQ = "FAULT_LEVEL_ID";// 故障等级序列

	public static final String FAULT_PREHANDLE_WAY_ID_SEQ = "FAULT_PREHANDLE_WAY_ID";// 故障现象（预处理）序列
	
	public static final String FAULT_RELATION_ID_SEQ = "FAULT_RELATION_ID";//故障关联关系主键
	
	public static final String SO_FAULT_EXT_ID_SEQ = "SO_FAULT_EXT_ID";	//故障处理信息主键
	
	/* 故障原因表 */
	public static final String FAULT_REASON_ID_SEQ = "FAULT_REASON_ID";// 主键值
	
	/* STEP_SPEC_PRPTY表 */
	public static final String SO_PRPTY = "SO_PRPTY";// COMPONENT_CODE值
	
	/* 故障种类与原因关系表 */
	public static final String FAULT_REASON_TO_TYPE_ID_SEQ = "FAULT_REASON_TO_TYPE_ID";// 主键值
	
	public static final String LOG_SG_SUBSYSTEM = "SG"; // SG子系统标识
	/********************************************	SAS END	*****************************************/

	public static final String BUTTON_TYPE = "B";//组件类型为Button
	
	public static final String COMPONENT_TYPE_IFRAME = "I";//组件类型为iframe
	
	public static final String COMPONENT_TYPE_PIECE = "P";//响应窗口控件
	
	public static final String SO_PROC_CHK_CONFIG_ID = "34110";//是否调用存储过程进行定单解析校验开关

	
	public static final String SYS_CONFIG_GROUP_SPEC="110011";//资源配置返回动作映射为新旧值的群组规格列表
	
	public static final String CHANGED_FLAG="Y";//用于标识在施工过程中是否对改属性进行了变更，
	//变更的信息一般需要回填CRM。变更后产生新的记录，将原记录标记为作废。Y 有变更 	N 未变更

	/******************************************** SA *****************************************/
	public static final String SA_QUERY = "SA_QUERY";		//gh系统中的保障单查询页面标志
	public static final String SA_CHANGE = "SA_CHANGE"; //gh系统中的保障单变更页面标志
	public static final String SA_CANCEL = "SA_CANCEL"; //gh系统中的保障单撤销页面标志
	public static final String SA_AUDIT = "SA_AUDIT"; //gh系统中的保障单审核页面标志 
	public static final String SA_RELATE = "SA_RELATE"; //gh系统中的保障单关联页面标志

	public static final String WO_GUI_SCENE_WO_HANDLE = "WO_HANDLE";//施工处理场景；
	public static final String WO_GUI_SCENE_SO_DISPLAY = "SO_DISPLAY";//定单详细信息展现；
	public static final String WO_GUI_SCENE_WO_RESPONSE = "WO_RESPONSE";//工单响应场景
	
	public static final String REF_FLAG_Y = "Y"; 	//经验故障中 典型故障
	public static final String REF_FLAG_N = "N";	//经验故障中 普通故障
	
	public static final String SA_FAULT_DETERMINE_STEP = "SP3059"; //保障单施工处理 故障定性环节
	
	public static final String SA_FAULT_DETERMINE = "SA_FAULT_DETERMINE";	//保障单施工处理 故障定性
	
	public static final String SA_FAULT_HANDLE_RATE = "SA_FAULT_HANDLE_RATE"; //保障单施工处理 处理进度
	
	public static final String SA_FAULT_SUCCESS_RETURN = "SA_FAULT_SUCCESS_RETURN"; //保障单施工处理 正常回
	
	public static final String SA_FAULT_HANDLE_WORK_TYPE = "137";	//工区类型为故障处理
	public static final String SA_FAULT_APPLY_WORK_TYPE = "390";	//工区类型为故障受理
	public static final String SA_FAULT_CONFIRM_WORK_TYPE = "315";	//工区类型为故障证实 modify by Baixd 315-->390
	public static final String SA_FAULT_CONFIRM_FAIL_REASON_PROC = "IVR.GET_FAIL_REASON4SO";	//工区类型为故障证实
	public static final String SA_FAULT_CONFIRM_FAIL_REASON_4040220 = "4040220";	//异常原因：退回故障分析
	
	public static final String SA_FAULT_RES_CHANGE_CONFIG_ID = "215001"; //保障单施工处理资源变更是否与资源连通 CONFIG_ID=215001 
	public static final String SA_FAULT_RES_CHANGE_Y = "Y"; //保障单施工处理资源变更标志 是
	public static final String SA_FAULT_RES_CHANGE_N = "N"; //保障单施工处理资源变更标志 否
	public static final String SA_FAULT_RES_CHANGE = "RES_CHANGE_Y";//资源变更

	public static final String SA_FAULT_CONFIRM_STEP = "SP3058"; //故障证实环节
	public static final String SA_FAULT_CONFIRM_Y = "SA_FAULT_CONFIRM_Y";//故障证实通过
	public static final String SA_FAULT_CONFIRM_N = "SA_FAULT_CONFIRM_N";//故障证实失败
	
	public static final String HANDLE_RATE_FEEDBACK = "401010"; //工单进度反馈
	
	public static final String MAX_MSG_CALL_COUNT = "34200";// 定单解析失败最大允许次数
	
	public static final String SA_FAULT_TEST_RESULT_SUCCESS = "SUCCESS"; //故障测试成功
	public static final String SA_FAULT_TEST_RESULT_FAIL = "FAIL"; //故障测试失败
	public static final String SA_FAULT_TEST_RESULT_CODE = "T"; //故障测试结果编码 
	
	public static final String SA_FAULT_TEST_RESULT_STEP_A = "SG2012"; //故障测试环节 自动测试
	public static final String SA_FAULT_TEST_RESULT_STEP_M = "SG2001"; //故障测试环节 人工测试
	
	public static final String CHG_SERV_SPEC_FOR_SHARE_LINE_NBR = "22001";// 客户服务编码（解析共线号码）

	public static final String PROD_FOR_SHARE_LINE_NBR = "22000";// 产品编码（解析共线号码）
	
	public static final String GROUP_SPEC_FOR_SHARE_LINE_NBR = "22002";// 群组类型（解析共线号码）
	public static final String COMPL_FLAG_Y = "Y";//同群组或同CO下无还未竣工的其他成员
	public static final String COMPL_FLAG_N = "N";//同群组或同CO下有还未竣工的其他成员
	
	public static final String REQ_RES_SYSTEM_QUERY_TYPE = "S"; //保障系统查询资源系统查询类型S：服务实例ID
	public static final String APPLICATION_SYSTEM_SAS = "SAS"; // 资源查询应用，服务保障系统
	
	public static final String SA_SENDER_TYPE = "S";
	
	public static final String CONFIRM_STYLE_MAN="M" ; //人工证实
	public static final String CONFIRM_RESULT_PASS="Y"; //故障证实通过
	public static final String CONFIRM_RESULT_FAIL="N"; //故障证实通过
	
	public static final String LIMITTD_QUERY_RECORD="110019" ; //限制最多查询条数的开关
	
	/**SAS调用CRM相关常量**/
	public static final String EID_CRM_SAS_QRY_CUST_REQ = "SAS_QRY_CUST_REQ"; // CRM接口编码
	public static final String XMLHEADER_SERIALNO_FOR_CRM_SEQ = "XMLHEADER_SERIALNO_FOR_CRM"; // CRM接口操作序列
	public static final String CRM_SECURITY_PRINCIPAL = "weblogic"; // CRM认证用户
	public static final String CRM_SECURITY_CREDENTIALS = "weblogic"; // CRM认证密码
	public static final String SENDER_FOR_SAS_TO_CRM = "SAS";

	/* 查询条件关键字 */
	public static final String KEY_FOR_QRY_CRM_ACC_NBR = "ACC_NBR";// ACC_NBR：业务号码
	public static final String KEY_FOR_QRY_CRM_ACCOUNT = "ACCOUNT";// ACCOUNT：账号
	public static final String KEY_FOR_QRY_CRM_CUST_ID = "CUST_ID";// CUST_ID：客户标识
	public static final String KEY_FOR_QRY_CRM_SERV_ID = "SERV_ID";// SERV_ID：服务实例
	public static final String KEY_FOR_QRY_CRM_PROD_CAT = "PROD_CAT";// PROD_CAT：网别，产品类别
	public static final String KEY_FOR_QRY_CRM_LOCAL_NET = "LOCAL_NET";// LOCAL_NET：本地网
	public static final String KEY_FOR_QRY_CS_SA_NBR = "SA_NBR"; //SA_NBR：外部定单号
	public static final String KEY_FOR_QRY_CS_SHARE_LINE_NBR = "SHARE_LINE_NBR"; //SHARE_LINE_NBR：共线号码

	/* 查询数据类型 */
	public static final String DATE_TYPE_FOR_QRY_CRM_N = "N"; // Number
	public static final String DATE_TYPE_FOR_QRY_CRM_C = "C"; // Char
	public static final String DATE_TYPE_FOR_QRY_CRM_D = "D"; // Date

	/* 查询条件匹配类型 */
	public static final String MATCH_TYPE_FOR_QRY_CRM_E = "E"; // E
																// EQUAL，精确匹配，相等
	public static final String MATCH_TYPE_FOR_QRY_CRM_I = "I"; // I
																// IN，对应多个VALUE
	public static final String MATCH_TYPE_FOR_QRY_CRM_L = "L"; // L
																// LIKE，模糊匹配，相似
	public static final String MATCH_TYPE_FOR_QRY_CRM_B = "B"; // B
																// BETWEEN，范围限定
	
	/*查询结果的节点名称*/
	public static final String RESULT_NODE_FOR_QRY_CRM_CUSTBASE = "CustBaseInfo"; // 客户资料信息
	public static final String RESULT_NODE_FOR_QRY_CRM_PROD_INFO = "ProdInfos"; // 产品信息
	public static final String RESULT_NODE_FOR_QRY_CRM_MAIN_PROD = "MainProdInfo"; // 主产品信息
	public static final String RESULT_NODE_FOR_QRY_CRM_SUB_PROD = "SubProdInfos"; // 附属产品信息
	public static final String RESULT_NODE_FOR_QRY_CRM_ADDR = "AddrInfos"; // 地址信息
	public static final String RESULT_NODE_FOR_QRY_CRM_ACC_NBR = "AccNbrInfos"; // 号码信息
	public static final String RESULT_NODE_FOR_QRY_CRM_SLA = "SLAInfos"; // SLA信息
	
	public static final String NOTIFY_TYPE_SM = "4"; // 通知类型
	public static final String NOTIFY_CONFIG_ID = "0"; // 通知类型notifyConfigId
	public static final String NOTIFY_TYPE_WO = "0"; // 通知类型 生成工单

	public static final String IS_SO_ARC = "34100"; // 定单归档
	public static final String IS_SO_ARC_Y = "Y";
	public static final String IS_SO_ARC_N = "N";

	public static final String HANDLE_TYPE_WO_PRESS = "600001";
	public static final String HANDLE_TYPE_WO_PRESS_INFO = "工单催办";
	public static final String HANDLE_TYPE_WO_SUCCESS_PRESS = "成功催单";
	
	public static final String HANDLE_MSG_WO_PRESS = "600002";
	
	public static final String HANDLE_TYPE_CS_URGE = "600020";
	public static final String HANDLE_TYPE_CS_URGE_INFO = "客服系统障碍催修";
	
	public static final String HANDLE_TYPE_NOTIFY_CS = "600021";//阶段通知客服系统
	
	public static final String HANDLE_TYPE_SA_REAPPLY = "501010";
	public static final String HANDLE_TYPE_SA_REAPPLY_INFO = "重复申告";
	public static final String HANDLE_TYPE_SA_TEST = "501020";
	public static final String HANDLE_TYPE_SA_TEST_INFO = "故障测试";
	public static final String HANDLE_TYPE_SA_PREHANDLE = "501030";
	public static final String HANDLE_TYPE_SA_PREHANDLE_INFO = "故障预处理";
	public static final String HANDLE_TYPE_SA_REPORT = "501040";
	public static final String HANDLE_TYPE_SA_REPORT_INFO = "故障上报";
	public static final String HANDLE_TYPE_SA_RELATE = "501050";
	public static final String HANDLE_TYPE_SA_RELATE_INFO = "关联其他障碍";
	public static final String HANDLE_TYPE_SA_RELATE_P = "501060";
	public static final String HANDLE_TYPE_SA_RELATE_P_INFO = "关联公共障碍";
	public static final String HANDLE_TYPE_SA_RELATE_S = "501070";
	public static final String HANDLE_TYPE_SA_RELATE_S_INFO = "关联大面积障碍";
	public static final String HANDLE_TYPE_SA_IDENTIFY = "501080";
	public static final String HANDLE_TYPE_SA_IDENTIFY_INFO = "识别大面积障碍";
	public static final String HANDLE_TYPE_SA_RELEASE = "501090";
	public static final String HANDLE_TYPE_SA_RELEASE_INFO = "解除障碍关联";
	public static final String USER_FAULT_TYPE_NAME="用户类障碍";
	public static final String HANDLE_TYPE_SA_ARCHIVE = "501095";
	public static final String HANDLE_TYPE_SA_ARCHIVE_INFO="申告单直接归档";
	public static final String HANDLE_TYPE_SA_ANALYSIS = "400041";
	public static final String HANDLE_TYPE_SA_ANALYSIS_INFO="故障自动分析";
	public static final String HANDLE_TYPE_SA_MODIFY = "501096";
	public static final String HANDLE_TYPE_SA_MODIFY_INFO = "障碍单改单";
	
	
	public static final String SYS_CONFIG_WSURL_SAS_CRM = "215002"; //CRM提供给SAS系统 webService地址
	
	public static final String SAS2CrmService = "Crm4SasService"; //CRM提供给SAS系统的webService名字
	
	public static final String PROD_CAT_TYPE_C = "1";  //网别，产品类别 固网
	public static final String PROD_CAT_TYPE_G = "0";  //网别，产品类别 G网
	
	public static final String STEP_ID_FAULT_ANALYSIS = "SG2008"; // 故障分析环节

	public static final String SURVEY_RESULT_ID_SEQ = "SURVEY_RESULT_ID";// 回访结果

	public static final String SURVEY_RESULT_DETAIL_ID_SEQ = "SURVEY_RESULT_DETAIL_ID";// 回访结果明细

	public static final String SURVEY_MATCH_ID_SEQ = "SURVEY_MATCH_ID";// 回访匹配

	public static final String SURVEY_TEMPLATE_ID_SEQ = "SURVEY_TEMPLATE_ID";// 回访模板

	public static final String SURVEY_ITEM_ID_SEQ = "SURVEY_ITEM_ID";//回访元素
	
	public static final String AUTO_VOICE_QUEUE_ID_SEQ = "AUTO_VOICE_QUEUE_ID";
	
	public static final String AUTO_VOICE_QUEUE_BUSI_TYPE_SP = "SP";//服务开通业务
	public static final String AUTO_VOICE_QUEUE_BUSI_TYPE_SA = "SA";//服务保障业务

	public static final String AUTO_VOICE_QUEUE_EVENT_TYPE_C = "C";//故障证实
	public static final String AUTO_VOICE_QUEUE_EVENT_TYPE_V = "V";//客户回访
	
	public static final String SO_FAULT_REPORT_FLAG_YES = "Y";//服务保障:故障上报
	public static final String SO_FAULT_REPORT_FLAG_NO = "N";//服务保障:不上报
	
	public static final String SO_FAULT_NOTIFY_FLAG_YES = "Y";//服务保障:通知
	public static final String SO_FAULT_NOTIFY_FLAG_NO = "N";//服务保障:不通知
	
	public static final String SO_FAULT_STS_WAIT ="A,D,E,M,W,t";//障碍处理状态：等待处理
	public static final String SO_FAULT_STS_PRC="B,F,g,h,I,K,P,s,T,U,V,Y";//障碍处理状态：正在处理
	public static final String SO_FAULT_STS_COM="C,S";//障碍处理状态：处理完成
	
	public static final String FAULT_OBJECT_TYPE_AREA = "AREA";//服务保障:故障对象类型(影响范围) 服务区
	public static final String FAULT_OBJECT_TYPE_EXCH = "EXCH";//服务保障:故障对象类型(影响范围) 局向
	public static final String FAULT_OBJECT_TYPE_MDF = "MDF";//服务保障:故障对象类型(影响范围) 配线架
	public static final String FAULT_OBJECT_TYPE_CCP = "CCP";//服务保障:故障对象类型(影响范围) 交接箱
	
	public static final String FAULT_OBJECT_TYPE_ACC_NBR = "ACC_NBR";//服务保障:故障对象类型(影响范围)
	//号码范围
	public static final String FAULT_RULE_TYPE_I = "I";// 障碍规则类型：拦截规则
	public static final String FAULT_RULE_TYPE_R = "R";// 障碍规则类型：识别规则
	public static final String AUTO_VOICE_QUEUE_STS_D = "D";//等待处理
	public static final String AUTO_VOICE_QUEUE_STS_P = "P";//语音流程提取数据，正在处理
	public static final String AUTO_VOICE_QUEUE_STS_E = "E";//语音处理异常，如电话无人接听等，需转人工施工处理
	public static final String AUTO_VOICE_QUEUE_STS_F = "F";//语音处理失败，需进行成功回单；
	public static final String AUTO_VOICE_QUEUE_STS_C = "C";//语音处理成功，需进行失败回单
	public static final String AUTO_VOICE_QUEUE_STS_R = "R";//作废
	public static final String AUTO_VOICE_QUEUE_M = "M";// 人工施工
	
	public static final String AUTO_VOICE_QUEUE_STSFLAG_CEF = "CEF"; //语音处理回单成功和失败标志
	public static final String AUTO_VOICE_QUEUE_STSFLAG_P = "P"; //语音处理异常标志
	
	public static final String AUTO_VOICE_QUEUE_BACKFILL_FLAG_F = "F"; //语音处理回单失败回笼标志
	public static final String AUTO_VOICE_QUEUE_BACKFILL_FLAG_Y = "Y"; //语音处理回单成功回笼标志
	
	public static final String SO_FAULT_COMPLAINT_STS_UNCOMP = "N"; //未投诉
	public static final String SO_FAULT_COMPLAINT_STS_UNPRESS = "D"; //已投诉待催修
	public static final String SO_FAULT_COMPLAINT_STS_PRESS = "C"; //完成催修

	/*巡检计划  赵丹 2010-3-4 */
	public static final String SYS_CONFIG_WORK_TYPE_ID = "110022";//线路巡检工区类型标识配置
	
		
	public static final String EXEC_CYCLE_TEMP = "C";//执行周期为临时
	public static final String EXEC_CYCLE_DAY = "D";//执行周期为日
	public static final String EXEC_CYCLE_WEEK = "W";//执行周期为周
	public static final String EXEC_CYCLE_MONTH = "M";//执行周期为月
	public static final String EXEC_CYCLE_YEAR = "Y";//执行周期为年
	public static final String EXEC_CYCLE_ALL = "A";//所有周期性计划
	
	public static final String EXEC_DATA_DEFAULT = "0";//执行年，月，周，日默认值为0
	public static final String PLAN_TYPE_ONCE = "O";//计划类型为一次性计划
	public static final String PLAN_TYPE_CYCLE = "C";//计划类型为周期性计划
	/* */
	public static final String DISABLE_RETURN_USERS = "215020";//服务保障，不允许回单的外线施工人员列表；

	public static final String PROD_CAT_ID_ADSL = "40"; //互联网业务
	
	/*--------------------领单加速-----------------------*/
	public static final String OVER_FETCH_YES = "Y";// 领单加速

	public static final String OVER_FETCH_NO = "N";// 正常领单
	public static final String MSG_TYPE_RP = "RP";// 卡管理定单的消息类型
	
	
	public static final String SYS_CONFIG_BOOKDAY_ID="31100";//预约能力查询条件预约时间查询范围
	public static final String INSTALL_WORK_TYPE_ID="7";//装机工区类型
	/*------------------------------DOM接口参数(paraMap)key值定义-----------------------------*/

	public static final String PARA_MAP_SO_SVO = "SoSVO";

	public static final String PARA_MAP_SERVICE_ORDER_ID = "serviceOrderId";
	
	public static final String PARA_MAP_INTER_SERVICE_ORDER_SVO = "InterServiceOrderSVO";

	public static final String PARA_MAP_INTER_SERVICE_ORDERS_SVO = "InterServiceOrdersSVO";

	public static final String PARA_MAP_INTER_MSG_CENTER_SVO = "InterMsgCenterSVO";

	public static final String PARA_MAP_SO_MSG_CENTER_SVO = "SoMsgCenterSVO";

	public static final String PARA_MAP_INTER_SO_EXCEPTION_SVO = "InterSoExceptionSVO";

	public static final String PARA_MAP_SCHEMA_SERVICE_ORDER = "ServiceOrder";

	public static final String PARA_MAP_SCHEMA_SERVICE_ORDERS = "ServiceOrders";

	public static final String PARA_MAP_SCHEMA_EXPT_SERVICE_ORDER = "ExptServiceOrder";

	public static final String PARA_MAP_SCHEMA_SERVICE_ORDER_RETURN = "ServiceOrderReturn";

	public static final String PARA_MAP_SCHEMA_CUST_ORDER = "CustOrder";

	public static final String PARA_MAP_SCHEMA_RESULT_INFO = "ResultInfo";

	public static final String PARA_MAP_EXT_SO_NBR = "extSoNbr";// 服务定单号码

	public static final String PARA_MAP_CO_NBR = "coNbr";// 客户订单号码

	public static final String PARA_MAP_SO_NBR = "soNbr";// 定单号码

	public static final String PARA_MAP_WO_NBR = "woNbr";// 工单号码

	public static final String PARA_MAP_SPEC_PRPTY_LIST = "specPrptyList";// 规格属性列表

	public static final String PARA_MAP_INPUT_FLAG_LIST = "inputFlagList";// 回填信息格式列表

	public static final String PARA_MAP_SPEC_PRPTY_VALUE_LIST = "specPrptyValueList";// 规格属性值列表

	public static final String PARA_MAP_PROD_INST_ID = "prodInstId";// 服务实例编码

	public static final String PARA_MAP_CO_NBR_LIST = "coNbrList";// 客户订单号码列表

	public static final String PARA_MAP_ORDER_TYPE = "orderType";// 客户订单类型

	public static final String PARA_MAP_ORDER_TYPE_A = "A";// 客户订单类型:新装

	public static final String PARA_MAP_ORDER_TYPE_M = "M";// 客户订单类型:改单

	public static final String PARA_MAP_ORDER_TYPE_B = "B";// 客户订单类型:过渡补卡单

	public static final String PARA_MAP_ACT_TYPE = "actType";// 动作类型

	public static final String PARA_MAP_RECIEVE_DATE = "receiveDate";// 客户订单接收时间

	public static final String PARA_MAP_PROD_ID_STR = "prodIdStr";// 产品组合
																	// 以逗号分隔

	public static final String PARA_MAP_PROD_ID = "prodId";// 产品ID

	public static final String PARA_MAP_LOCAL_NET_ID = "localNetId";// 本地网

	public static final String PARA_MAP_EXT_WO_NBR = "extWoNbr";// 外部工单号

	public static final String PARA_MAP_SCHEMA_QRY_WOS_OF_SO = "QryWosOfSo";

	public static final String PARA_MAP_BOOK_DATE_TIME = "bookDateTime";// 预约时间

	public static final String PARA_MAP_COMP_DATE_TIME = "compDateTime";// 竣工时间

	public static final String PARA_MAP_SERV_INST_ID = "servInstId";// 服务实例ID

	public static final String PARA_MAP_EXCH_ID = "exchId";// 局向ID

	public static final String PARA_MAP_AREA_ID = "areaId";// 地域ID

	public static final String PARA_MAP_CHG_SERV_SPEC_ID = "chgServSpecId";// 客户服务ID

	public static final String PARA_MAP_WORK_AREA_ID = "workAreaId";// 工区ID

	public static final String PARA_MAP_STAFF_ID = "staffId";// 员工ID
	
	public static final String PARA_MAP_STAFF_NAME = "staffName";// 员工姓名

	public static final String PARA_MAP_RETURN_STR = "retStr";// 返回值

	public static final String PARA_MAP_RETURN_LIST = "retList";// 返回列表

	public static final String PARA_MAP_SCHEMA_SO_SI_GROUP_CHANGE_ASK = "SoSiGroupChangeAsk";

	public static final String PARA_MAP_RESULT_CODE = "resultCode"; // 接口返回值

	public static final String PARA_MAP_RESULT_INFO = "resultInfo"; // 接口返回信息

	public static final String PARA_MAP_SCHEMA_RES_RESULT = "result"; // 接口返回协议

	public static final String PARA_MAP_WO_SVO = "WoSVO";

	public static final String PARA_MAP_CONTROL_INFO_TYPE = "ControlInfoType";

	public static final String PARA_MAP_RES_SERV_INFO_TYPE = "ResServInfoType";

	public static final String PARA_MAP_OPER_FALG = "operFlag";

	public static final String PARA_MAP_NO_FLAG = "noFlag";

	public static final String PARA_MAP_RUN_STS = "runSts";

	public static final String PARA_MAP_IS_SELF = "isSelf";

	public static final String PARA_MAP_RESOURCE_ARCHIVE_REQUEST = "ResourceArchiveRequest";

	public static final String PARA_MAP_SO_PRIORITY_LIST = "soPriorityList";// 定单优先级列表

	public static final String PARA_MAP_XML_TYPE = "xmlType";

	public static final String PARA_MAP_XML = "xml";
	
	public static final String PARA_MAP_BACK_FILL_LIST = "backFillList";

	public static final String PARA_MAP_BACK_FILL_INFO = "backFillInfo";

	public static final String PARA_MAP_BACK_FILL_ID = "backFillId";
	
	public static final String PARA_MAP_BACK_FILL_OBJ = "backfillObj";
	public static final String SYNC_MAINT_AREA_TO_RMS = "35010"; // 是否向资源同步维护区数据
	public static final String SYNC_MAINT_AREA_TO_RMS_Y = "Y"; // 向资源同步维护区数据
	public static final String SYNC_MAINT_AREA_RESULT_CODE = "0";//向资源系统同步数据时资源返回值
	
	public static final String SYS_CONFIG_IS_QUERY_HIS = "215021";// 从crm侧打开工单列表时，是否需要关联历史表进行查询
	
	//2010-8 lilin start
	public static final String DELIVER_FLAG_YES = "Y";
	public static final String DELIVER_FLAG_NO = "N";

	//2010-8 lilin end
    //资源变更 add by liyaquan
	public static final String MSG_ORDER_ZYBG = "ZYBG";//资源变更
	public static final String MOBILE_ZYBG = "ZSZYBG";//掌上运维资源变更
	public static final String EID_RES_CHG_RES_PRPTY = "USED_RES_CHANGE_REQ"; //资源变更请求
	public static final String FUNCODE_RES_CHANGE_REQUEST = "resChangeRequest"; //资源变更
	public static final String HANDLE_TYPE_CHG_RES_PRPTY = "400700"; //短信修改资源
	public static final String SO_STS_P = "P";
	/*工单资源变更标志*/
    public static final String WO_RES_CHANG_FALG = "Y";
    public static final String SYS_CONFIG_WO_HANLDE_QUERY_LOG = "215038";//施工处理查询操作日志是否记录开关
	
    
    /*********************移动平台用***************************************/
    public static final String SYS_NAME = "TM";    
    public static final String COLUMN_SEQ = "WO_NBR";  
	public static final String SO_CAT_BUSINESS = "1";// 营业受理类开通
	public static final String SO_CAT_FAULT = "2";// 服务保障类
	public static final int DEFAULT_PAGE_SIZE_MOBILE=50;
	public static final String WO_SEARCH = "woSearch";
	
	public static final String SA_COMPLAINT_C = "C";
	public static final String FAULT_LEVEL_COMPLAINT = "10140";
	
	/*********************瑞达北向接口*************************************/
	public static final String SA_PROD_CAT_INTER_ACCESS = "40";
	public static final String SA_TEST_CONTENT_CODE_AD = "1";
	public static final String SA_TEST_CONTENT_CODE_AG = "2";
	public static final String SA_TEST_MODE_A = "A";
	public static final String SA_TEST_MODE_M = "M";
	public static final String SA_TEST_TYPE_T = "T";
	public static final String SA_TEST_SYSTEM_112 = "112";
	public static final String SA_TEST_CONTENT_CODE_L = "L";
	public static final String SA_TEST_CONTENT_CODE_R = "R";
	public static final String FAIL_REASON_FOR_TEST = "T";
	
	public static final String RELATION_FLAG_OBJ = "RELATION_OBJ"; //障碍关联标志，源
	public static final String RELATION_FLAG_RES = "RELATION_RES"; //障碍关联标志，目标
	
	/****停复，资源返回是否存库，还是文件，还是不存*****/
	
	public static final String INTER_FILE_PATH = "72001";
	public static final String INTER_FILE_SAVE_METHOD = "72000";
	public static final String SAVE_METHOD_DB = "DB";
	public static final String SAVE_METHOD_NULL = "NULL";
	public static final String SAVE_METHOD_FILE = "FILE";
	public static final String TAB_SUB_SELECT_FLAG_Y = "Y";
	public static final String TAB_SUB_SELECT_FLAG_N = "N";
	
	public static final String SYS_CONFIG_PHASE_NOTIFY_FLAG = "215047"; //客服系统阶段通知开关
	
	public static final String SYS_CONFIG_REPLY_WO_FLAG = "215048"; //客服系统工单回复开关
	
	public static final String SYS_CONFIG_IVR_REPLY_WO_FLAG = "215049"; //语音受理进行工单回复开关
	
	public static final String SYS_CONFIG_WSURL_SAS_CS = "215003"; //CS提供给SAS系统 webService地址
	
	public static final String SAS2CsService = "ossWebservice"; //CS提供给SAS系统的webService名字
	//交资管理，端口联动
    public static final String SYS_CONFIG_RES_BUILD_ADDR = "40012"; //交资管理资源配置地址
    public static final String EID_CHANGED_RES_SERV_REQ = "CHANGED_RES_SERV_REQ"; //查询资源服务请求
    public static final String EID_RES_BUILD_RETURN_WO = "RES_BUILD_RETURN"; //交资管理资源调用成功回单接口
    public static final String FUNCODE_CHANGED_RES_QUERY_REQ = "changedResServReq";//资源服务查询请求
    public static final String RESOUCE_BUILD_CHG_SERV_SPEC_ID = "3000000"; //交资管理客户服务
    public static final String RB_FAIL_REASON_4040404 = "4040404"; //异常原因通知rms撤单
    public static final String SO_CAT_RESOUCE_BULID = "9";//交资管理类
    public static final String SO_CAT_PORT_CHANGE = "5";//端口联动类	
    public static final String STEP_ID_CHANGE_QUERY_RES = "SC0008"; //资源查询环节
    public static final String STEP_ID_RES_ARC_REQ = "SC0009";
    public static final String EID_SO_FAIL_RETURN_TO_RMS = "SO_FAIL_RETURN_TO_RMS"; //退端口联动单到RMS
    public static final String EID_SO_RESBUILD_FAIL_RETURN_TO_RMS  = "SO_RESBUILD_FAIL_RETURN_TO_RMS";//交资退回RMS
    public static final String FUNCODE_SO_FAIL_RETURN_TO_RMS = "soFailReturnToRMS"; //调用方法
    public static final String FUNCODE_SO_RESBUILD_FAIL_RETURN_TO_RMS = "soResBuildFailReturnToRMS"; //交资退回RMS方法
    public static final String ZYJS_LINK_RMS_USERNAME = "gc";
    public static final String ZYJS_LINK_RMS_PASSWORD = "gc";
	public static final String ZYJS_SYSTEM_FLAG = "RB_QUERY";

	public static final String SYS_CONFIG_RMS_BUILD_DEPT = "225049";// 资源建设部门
	
	//属性适用场景 SPEC_PRPTY表中的FOR_SCENE字段值
	
	public static final String SPEC_PRPTY_FOR_SCENE_EQPT_PROD = "EQPT_PROD"; //终端信息
	public static final String OBJECT_TYPE_SPONSOR_STAFF = "R"; //发起人

	// 河南移动：传输网管接口  add by baijm 2011-1-6
	// 电路调度接口
	public static final String NRMS_ARCHIVE_ATTEMP_CIRUIT = "archiveAttempCiruit" ;
	public static final String NRMS_UPDATE_SHEET_STATE = "updateSheetState";
	
	public static final String NRMS_GENERATE_TRAPH_ATTEMP_SHEET = "generateTraphAttempSheet";
	public static final String NRMS_UPDATE_APPLY_FAILURE_CIRCUIT = "updateApplyFailureCircuit";
	public static final String NRMS_UPDATE_DESIGN_FAILURE_CIRCUIT = "updateDesignFailureCircuit";
	public static final String NRMS_UPDATE_CIRCUIT_CONSTRUCT_RESULT = "updateCircuitConstructResult";
	
	public static final String NRMS_UPDATE_ATTEMP_SHEET_ARCHIVE = "updateAttempSheetArchive";
	public static final String NRMS_GET_ATTEMP_CIRUIT = "getAttempCiruit";

	public static final String NRMS_SERVICE = "TnmsCircuitService"; // 传输网管提供电路调度接口的webservice名字
	
	// 光路调度接口
	public static final String NRMS_OPTICAL_ARCHIVE_ATTEMP_OPTICAL_WAY = "archiveAttempOpticalWay";
	public static final String NRMS_OPTICAL_UPDATE_APPLY_FAILURE_OPTICAL_WAY = "updateApplyFailureOpticalWay";
	public static final String NRMS_OPTICAL_GENERATE_OPTICAL_WAY_APPLY_SHEET = "generateOpticalWayApplySheet";
	
	public static final String NRMS_OPTICAL_SERVICE = "TnmsOpticalWayService"; // 传输网管提供光路调度接口的webservice名字
	
	public static final String NRMS_RESULT_CODE_SUCCESS = "1"; // 调用成功
	public static final String NRMS_RESULT_CODE_FAIL = "0"; // 调用失败
	
	public static final String SYS_CONFIG_NRMS_RES_PRPTY_ID = "80020"; // 传输电路资源属性列表
	public static final String SYS_CONFIG_NRMS_CIRCUIT_NAME= "80025"; // 传输电路资源属性：电路名称
	
	public static final String NRMS_FAIL_REASON_ID = "85000"; // 与传输网管接口定义异常原因编码
	
	public static final String NRMS_STEP_ID_ACTION = "RA0003" ;  // 传输电路调度资源配置环节定义
	public static final String NRMS_OPTICAL_STEP_ID_ACTION = "RA" ; // 传输光路调度资源配置环节定义
	
	// 传输网管接口参数取值
	public static final String NRMS_SER_SUPPLIER = "HA_TNMS"; // 服务提供方
	public static final String NRMS_SER_CALLER = "HA_IRMS"; // 服务调用方
	public static final String NRMS_USERNAME = "4A"; // 用户名
	public static final String NRMS_PASSWORD = "票据"; // 口令
	
	// 短信网关接口,对参数SystemId，进行定义，业务开通系统Id，自己定义，用于与其他系统区分；
    public static final String NRMS_SYSTEM_ID = "sps"; 
    
    // 超期工单派发短信，超过预警时间是否派发短信
    //public static final String SYS_CONFIG_NRMS_IS_QUERY_PRE_ALARM_DATE = "80030";
    
    // 传输电路返回电路信息导出Excel头字段定义
    public static final String NRMS_FOR_EXCEL_EXT_SO_NBR = "定单号码";
    public static final String NRMS_FOR_EXCEL_CIRCUIT_NBR = "电路编号";
    public static final String NRMS_FOR_EXCEL_CIRCUIT_PRPTY_ID = "电路属性ID";
    public static final String NRMS_FOR_EXCEL_CIRCUIT_NAME = "电路属性名称";
    public static final String NRMS_FOR_EXCEL_CIRCUIT_PRPTY_VALUE = "电路属性值";
    
    public static final String NRMS_FOR_EXCEL_EN_EXT_SO_NBR = "soPwcMVO.getExtSoNbr()";
    public static final String NRMS_FOR_EXCEL_EN_CIRCUIT_NBR = "soPwcMVO.getCircuitNbr()";
    public static final String NRMS_FOR_EXCEL_EN_CIRCUIT_PRPTY_ID = "soPwcMVO.getSpecPrptyId()";
    public static final String NRMS_FOR_EXCEL_EN_CIRCUIT_NAME = "soPwcMVO.getSpecPrptyName()";
    public static final String NRMS_FOR_EXCEL_EN_CIRCUIT_PRPTY_VALUE = "soPwcMVO.getSpecPrptyValue()";
	
    // 河南业务开通用到，先进行同步
    public static final String SYS_CONFIG_WRITE_XML_4_RMS = "40045";// 资源查询、配置返回XML是否入库
	public static final String RES_XML_MSG_XML_TYPE_A = "A";// 资源配置
	public static final String RES_XML_MSG_XML_TYPE_Q = "Q";// 资源查询
	public static final String RES_XML_MSG_XML_TYPE_D = "D";// 资源设计
	public static final String WO_NBR_SUFFIX = "_R";// 工单编号后缀，用于失败回单资源释放map传递

	/*********************河南移动相关接口***********************************/
	/********************* 定单接收 begin ***********************************/
	public static final String HA_CRM = "HA_CRM";
	public static final String HA_EOMS = "HA_EOMS";
	public static final String CALLER_CRM_PWD = "123";
	public static final String DEFAULT_VALUE_ZERO = "0";// 非空字段默认值
	
	///////////////////////////////// SO ////// ///////////////////////////
	public static final String CITY_NAME = "cityName"; // localNetId
	public static final String COUNTY_NAME = "countyName"; // areaId
	public static final String SERIAL_NO = "serialNo"; // extSoNbr
	public static final String URGENT_DEGREE = "urgentDegree"; // priority
	public static final String SERVICE_TYPE = "serviceType"; // prodId
	public static final String SHEET_TYPE = "sheetType"; //chgServSpecId
	public static final String USER_ID = "userId"; // servInstId
	public static final String DEAL_TIME1 = "dealTime1"; // applDate
	public static final String DEAL_TIME2 = "dealTime2"; // 要求完成时间
	public static final String APPLY_INFO = "applyInfo"; // remarks
	public static final String BDEPT_CONTACT = "bdeptContact"; // 发展参与者名称
	public static final String BDEPT_CONTACT_PHONE = "bdeptContactPhone"; //发展人联系信息
	public static final String CMANAGER_PHONE = "cManagerPhone"; //受理员工名称
	public static final String CMANAGER_CONTACT_PHONE = "cManagerContactPhone"; //客户经理联系电话(客户联系结果信息)
	
	///////////////////////////////// SO_ADDR ////// //////////////////////
	public static final String CITY_A = "cityA"; // A端地址
	public static final String CITY_A_ADDRESS = "cityAAddress"; //A端详细地址
	public static final String CITY_Z = "cityZ"; // Z端地址
	public static final String CITY_Z_ADDRESS = "cityZAddress"; //Z端详细地址
	public static final String CUSTOM_AP_ADDRESS = "customAPAddress"; //详细地址
	
	///////////////////////////////// SO_CUST ////// ///////////////////////
	public static final String CUSTOM_NO = "customNo"; //custId
	public static final String CUSTOM_NAME = "customName"; //soCust.custName
	public static final String CUSTOM_LEVEL = "customLevel"; //soCust.customLevel
	public static final String CUSTOM_CONTACT = "customContact"; // 联系人
	public static final String CUSTOM_CONTACT_PHONE = "customContactPhone"; // 联系信息
	
	/********************* 定单竣工 begin ***********************************/
	public static final String N_DEPT_CONTACT = "ndeptContact"; // 网络部门联系人
	public static final String N_DEPT_CONTACT_PHONE = "ndeptContactPhone"; // 网络部门联系人电话
	public static final String DEAL_RESULT = "dealResult"; // 处理结果
	public static final String DEAL_DESC = "dealDesc"; // 处理说明
	public static final String APN_ID = "apnID"; // apnID
	public static final String APN_ADDRESS = "apnAddress"; // apn地址池
	public static final String GRE_IP = "greIp"; // GRE IP address（tunnel ip address）
	public static final String GATEWAY_USER_NAME = "gatewayUserName"; // 登录网关用户名
	public static final String GATEWAY_USER_PASSWORD = "gatewayUserPassword"; // 登录网关密码
	public static final String CIRCUIT_CODE = "circuitCode"; // 传输专线电路代号
	public static final String TEST_REPORT = "testReport"; // 测试报告
	public static final String CMNET_ALLOT_PORT = "CMNETAllotPort"; // CMNET节点机分配端口号
	public static final String ALLOT_BROADBAND = "allotBroadBand"; // 集团客户配置带宽
	public static final String ALLOT_BROADBAND_POSSESS_MODE = "allotBroadBandPossessMode"; // 集团客户带宽占有方式
	public static final String IP_ADRESS_NUMBER = "IPAdressNumber"; // 私有IP地址分配数
	public static final String IP_ADRESS = "IPAdress"; // 私有IP地址
	public static final String CHANGE_ALLOT_BROADBAND = "changeAllotBroadBand"; // 业务变更后配置带宽
	public static final String CHANGE_ALLOT_BROADBAND_POSSESS_MODE = "changeAllotBroadBandPossessMode"; // 业务变更后带宽占用方式
	public static final String CUSTOM_EQUIPMENT_AREA = "customEquipmentArea"; // 业务变更后集团客户设备放置详细位置
	public static final String NET_RES_CAPACITY = "netResCapacity"; // 网络资源能力确认
	public static final String CLIENT_PGM_CAPACITY = "clientPgmCapacity"; // 客户端工程能力确认
	public static final String INVEST_EVALUATE = "investEvaluate"; // 预计投资
	public static final String EXPECT_FINISH_DAYS = "expectFinishdays"; // 预计完成天数
	public static final String IS_OCCUPIED = "isOccupied"; // 是否已预占
	public static final String RES_IF_FULL = "resIfFull"; // 资源是否满足
	public static final String REMARK = "remark"; // 备注
	
	public static final String N_DEPT_CONTACT_CH_NAME = "网络部门联系人"; // 网络部门联系人
	public static final String N_DEPT_CONTACT_PHONE_CH_NAME = "网络部门联系人电话"; // 网络部门联系人电话
	public static final String DEAL_RESULT_CH_NAME = "处理结果"; // 处理结果
	public static final String DEAL_DESC_CH_NAME = "处理说明"; // 处理说明
	public static final String APN_ID_CH_NAME = "apnID"; // apnID
	public static final String APN_ADDRESS_CH_NAME = "apn地址池"; // apn地址池
	public static final String GRE_IP_CH_NAME = "GRE IP address（tunnel ip address）"; // GRE IP address（tunnel ip address）
	public static final String GATEWAY_USER_NAME_CH_NAME = "登录网关用户名"; // 登录网关用户名
	public static final String GATEWAY_USER_PASSWORD_CH_NAME = "登录网关密码"; // 登录网关密码
	public static final String CIRCUITCODE_CH_NAME = "传输专线电路代号"; // 传输专线电路代号
	public static final String TESTREPORT_CH_NAME = "测试报告"; // 测试报告
	public static final String CMNETALLOTPORT_CH_NAME = "CMNET节点机分配端口号"; // CMNET节点机分配端口号
	public static final String ALLOTBROADBAND_CH_NAME = "集团客户配置带宽"; // 集团客户配置带宽
	public static final String ALLOTBROADBANDPOSSESSMODE_CH_NAME = "集团客户带宽占有方式"; // 集团客户带宽占有方式
	public static final String IPADRESSNUMBER_CH_NAME = "私有IP地址分配数"; // 私有IP地址分配数
	public static final String IPADRESS_CH_NAME = "私有IP地址"; // 私有IP地址
	public static final String CHANGEALLOTBROADBAND_CH_NAME = "业务变更后配置带宽"; // 业务变更后配置带宽
	public static final String CHANGEALLOTBROADBANDPOSSESSMODE_CH_NAME = "业务变更后带宽占用方式"; // 业务变更后带宽占用方式
	public static final String CUSTOMEQUIPMENTAREA_CH_NAME = "业务变更后集团客户设备放置详细位置"; // 业务变更后集团客户设备放置详细位置
	public static final String NETRESCAPACITY_CH_NAME = "网络资源能力确认"; // 网络资源能力确认
	public static final String CLIENTPGMCAPACITY_CH_NAME = "客户端工程能力确认"; // 客户端工程能力确认
	public static final String INVESTEVALUATE_CH_NAME = "预计投资"; // 预计投资
	public static final String EXPECTFINISHDAYS_CH_NAME = "预计完成天数"; // 预计完成天数
	public static final String ISOCCUPIED_CH_NAME = "是否已预占"; // 是否已预占
	public static final String RESIFFULL_CH_NAME = "资源是否满足"; // 资源是否满足
	public static final String REMARK_CH_NAME = "备注"; // 备注
	
	/*********************河南移动相关接口end***********************************/
    // add by baijm 2011-09-02 河南移动业务开通CRM接口需要，进行同步
	public static final String FOR_SCENE_SO_PARSE_CHECK = "SO_PARSE_CHECK";// 定单解析校验
	
	static public final String ENABLE = "0";// 有效
	static public final String UNABLE = "1";// 无效
	
	public static final String MSG_TYPE_PM = "PM";//so_msg_center.msg_type（湖北版本）
	
	public static final String SO_TYPE_BIDE = "W";// 待装单（湖北需求，由CRM传递待装标记，服务开通接收定单后不进行流程匹配及启动直接转入待装。）

	public static final String CHARGE_FOR_FRONT = "1";// 前台收费
	public static final String CHARGE_FOR_BACKGROUND = "3";// 装机收费


	
	public static final String SYS_CONFIG_LOCAL_VISION_4_NM = "nm";
	public static final String SYS_CONFIG_SPEC_PRPTY_ACCESS_MODE = "60000";
	
	public static final String SYS_CONFIG_IS_JMS = "80001"; // 实时通道是否使用JMS
	public static final String EID_RES_RELEASE_4_JZ = "JZ_RES_RELEASE_REQ";
	public static final String FUNCODE_RES_RELEASE_4_JZ_REQUEST = "resCancellationRequest";
	public static final String EID_RES_RETURN_WO_RESP = "RES_RETURN_WO_RESP"; //交资回单
	
	public static final String SO_PROCESS_DO_INSTANCE_TRUE = "Y";
	public static final String SO_PROCESS_DO_INSTANCE_FALSE = "N";
	public static final String HANDLE_TYPE_SA_INTERCEPT = "501091";//故障自动拦截
	public static final String HANDLE_TYPE_SA_RECOGNIZE = "501092";//故障自动识别
	
	public static final String SO_PROC_INST_ID_NO_INSTANCE = "-1";
	
	//默认是否实例化
	public static final String DO_INSTANCE_Y = "Y";
	public static final String DO_INSTANCE_N = "N";
	public static final String CAN_NO_INSTANCE_Y = "Y";
	public static final String CAN_NO_INSTANCE_N = "N";
	
	//流程发布状态
	
	public static final String AVAILABLE = "A"; //等待审批
	
	public static final String CHECKED = "C"; //等待审批

	public static final String PUBLISHED = "P"; //等待审批
	
	public static final String WO_PRESS_TYPE_WO_GROUP = "W";
	
    public static final String SYS_CONFIG_CONTEXT_ROOT = "20000";
    
    public static final String SERV_DEPT_AREA_TYPE = "M";  //M 营销中心
    
    public static final String PROD_ID_SX="215047";//山西同退问题产品配置
    
    public static final String PARENT_MAIT_AREA_ID_TYPE="0";//父目标市场为零代表为虚拟目标市场
    
    public static final String SYS_CONFIG_SERV_DEPT_LOCAL_NET_ID = "216003"; // 山西是否割接网格临时配置
    
    public static final String PARA_MAP_DATE = "date";
    
    public static final String BUSI_STS_WAIT = "W";// 预约等待
    
    public static final String SYS_CONFIG_IS_SEND_WO_AT_NOW = "71030";// 预约工单是否立即派单
    public static final String SYS_CONFIG_SEND_WO_TIME_CONFIG = "71031";// 预约工单派单时间设置
    
    public static final String CHG_SERV_SPEC_ID_A="10";//客服投诉业务类型根据客户服务判断宽带装机
    public static final String CHG_SERV_SPEC_ID_X=",269,270,271,272,273,274,275,276,277,281,";//客服投诉业务类型根据客户服务判断 宽带移机
    
    public static final String SYS_CONFIG_WSURL_CUSTOM = "110005"; // 客服系统提供webservice的URL
    
    public static final String WO_PRESS_TYPE_BOOK="BOOK";
    
    public static final String SYS_CONFIG_IS_WAIT_PROD_ID="216004";
    
    public static final String SYS_CONFIG_IS_VOICE_PROD_ID="51131";
    public static final String SYS_CONFIG_ZHUANGJI_STEP="51132";
    public static final String SYS_CONFIG_IS_ADSL_PROD_ID="51133";
    public static final String SYS_CONFIG_KUANDAI_STEP="51134";
    
    public static final String SYS_CONFIG_RES_PRPTY_WIRING_CABLE_NAME = "34071";// 3000006 配线电缆名称
	public static final String SYS_CONFIG_RES_PRPTY_WIRING_CABLE_PAIR = "34072";// 3000007 配线电缆线序
	public static final String SYS_CONFIG_RES_PRPTY_TRUNK_CABLE_NAME = "34073";// 3000022 主干电缆名称
	public static final String SYS_CONFIG_RES_PRPTY_TRUNK_CABLE_PAIR = "34074";// 3000023 主干电缆线序
	
	public static final String ACC_NBR_PRPTY_WIRING_CABLE_NAME="3000006";//配线电缆名称
	public static final String ACC_NBR_PRPTY_WIRING_CABLE_PAIR="3000007";//配线电缆线序
	public static final String ACC_NBR_PRPTY_TRUNK_CABLE_NAME="3000022";//主干电缆名称
	public static final String ACC_NBR_PRPTY_TRUNK_CABLE_PAIR="3000023";//主干电缆线序
    
    /**掌上运维**/
    
    public static final String PARA_MAP_ADVQUERY_MVO = "AdvQueryMVO";
    public static final String PARA_MAP_PAGINFO = "PagInfo";
    public static final String PARA_MAP_COLUMN_NAME = "culmName";
    public static final String PARA_MAP_WOQUERY_MVO = "WoQueryMVO";
    public static final String PARA_MAP_WO_LIST = "woList";
    public static final String PARA_MAP_RET_TYPE = "retType";
    public static final String PARA_MAP_PARAM_MAP = "paramMap";
    public static final String PARA_MAP_SO_CAT = "soCat";
    public static final String PARA_MAP_WO_STAFF_ID = "woStaffId";
    public static final String PARA_MAP_FAIL_WO_MSG = "failWoMsg";
    public static final String PARA_MAP_STEP_FAIL_REASON_SVO = "stepFailReasonSVO";
    public static final String PARA_MAP_STEP_FAIL_REASON_LIST = "stepFailReasonList";
    public static final String PARA_MAP_SYS_USER_MVO = "sysUserMVO";
    public static final String PARA_MAP_SYS_USER_EXTENDED_MVO = "SysUserExtendedMVO";
    public static final String PARA_MAP_WORK_AREA_MVO = "WorkAreaMVO";
    public static final String PARA_MAP_WORK_AREA_SVO = "WorkAreaSVO";
    public static final String PARA_MAP_SYSTEM_NAME = "SystemName";
    public static final String PARA_MAP_WORK_AREA_LIST = "workAreaList";

    /***Native MOS App**/
    public static final String MOS_FEED_BACK_ID_SEQ = "MOS_FEED_BACK_ID";
    public static final String MOS_CALL_LOG_ID_SEQ = "MOS_CALL_LOG_ID";
    
    public static final String MOS_SURVEY_ID_SEQ = "MOS_SURVEY_ID";
    public static final String STAFF_LOCATIOIN_ID_SEQ = "STAFF_LOCATIOIN_ID";
    
//    public static final String SYS_CONFIG_MOS_SERVER_ADDR = "6000002";//MOS系统服务端webservice地址
    public static final String SYS_CONFIG_MOS_FLAG_ADDR = "6000045";//判断是否用iom还是用资源系统
    public static final String SYS_CONFIG_MOS_SERVER_ADDR = "6000005";//MOS系统服务端调用西安webservice地址
    public static final String SYS_CONFIG_MOS_LOCAL_SERVER_ADDR = "6000019";//Mos系统服务端调用自己的webservice地址
    public static final String MOS_SERVICE_ADDR_FOR_RMS_TEMP = "6000027";//Mos系统服务端调用资源的webservice地址（调试资源核查时使用）
//    public static final String SYS_CONFIG_MOS_SERVER_ADDR = "http://10.93.38.77:8080/trms/services/RmsForMosService";//MOS系统服务端调用西安webservice地址
//    public static final String SYS_CONFIG_MOS_LOCAL_SERVER_ADDR = "http://10.93.38.71:7013/web_mos/services/";//Mos系统服务端调用自己的webservice地址
    public static final String SYS_CONFIG_MOS_DETail_DATA_ID = "6000007";//MOS 详情的数据id
    public static final String SYS_CONFIG_MOS_APK_PATH = "6000003";//MOSapk下载地址
    public static final String SYS_CONFIG_MOS_JFORUM_ADDR = "6000010";//知识库地址
    public static final String FUNCODE_LOGIN="login"; //登录
    public static final String FUNCODE_FIND_DEFAULT_WORK_AREA="findDefaultWorkArea"; //默认工区
    public static final String FUNCODE_WO_FETCH = "woFetch";//领单
    public static final String FUNCODE_WO_RETURN_INIT = "woInitReturn";//初始化回单界面
    public static final String FUNCODE_WO_RETURN_FAIL = "woFailReturn";//失败回单 
    public static final String FUNCODE_WO_RETURN_SUCCESS = "woReturnSuccess";//成功回单 
    public static final String FUNCODE_LOCAL_NET="localNet";//切换工区一级页面--本地网
    public static final String FUNCODE_WORK_AREA="workArea";//切换工区二级页面--工区
    public static final String FUNCODE_WO_INIT_WOLIST = "initWoList";//工单查询
    public static final String FUNCODE_WO_INIT_WOLISTS = "initWoLists";//初始化工单处理界面的工单列表
    public static final String FUNCODE_INIT_SERV_DEPT = "initServDept";//初始化网格维护
    public static final String FUNCODE_MODI_SERV_DEPT = "modiServDept";//修改网格维护
    public static final String FUNCODE_CHG_SERV_DEPT = "chgServDept";//网格数据联动
    public static final String FUNCODE_STAFF = "queryStaff";//查询所有员工
    public static final String FUNCODE_WO_Detail = "initWoDetail";//工单详情
    public static final String FUNCODE_RESERVE_DATE_TIME = "reserveDateTime";//预约功能
    public static final String FUNCODE_CHANGE_LOCAL_NET = "changeLocalNet";//转派中的本地网列表
    public static final String FUNCODE_WORK_AREA_TYPE = "workAreaType";//获得工区类型
    public static final String FUNCODE_CHANGE_WORK_AREA = "changWorkArea";//转派中的工区列表
    public static final String FUNCODE_CHANGE_FAIL_REASON = "failReason";//转派中的转派原因
    public static final String FUNCODE_WORK_STAFF = "workStaff";//转派中的施工人员列表
    public static final String FUNCODE_CHANGE_ORDER_SUCCESS = "changeOrderSuccess";//转派成功
    public static final String FUNCODE_FIND_BULLETIN_LIST= "findBulletin";//获取公告列表
    public static final String FUNCODE_SET_DEFAULT_WORK_AREA = "setDefaultWorkArea";//设置默认工区
    public static final String FUNCODE_GET_BIZ_FILTER_OPTION= "getBizFilterOption";//获取业务筛选选项
    public static final String FUNCODE_FEED_BAK = "feedBack";//用户反馈
    public static final String FUNCODE_FIND_WO_BAT = "findWoBat";
    public static final String FUNCODE_MOD_FEEDBACK_WO = "modFeedbackWo";
    public static final String FUNCODE_MSG_SEND = "msgSend";//短信通知
    public static final String FUNCODE_STATIS_REPORT="statisReport";//統計報表
    public static final String SYS_CONFIG_MOS_SEND_LOCATION_INTERVAL= "6000000";//工单处理的业务筛选选项列表
    public static final String SYS_CONFIG_MOS_STAFF_LOCATION_INTERVAL= "6000024";
    public static final String SYS_CONFIG_MOS_STAFF_LOCATION_ONCE_MAXNUM= "6000022";
    public static final String SYS_CONFIG_MOS_STAFF_LOCATION_SUBMIT_NUM= "6000023";
    public static final String FUNCODE_WO_LIST = "woList";//工单列表
    public static final String FUNCODE_MOS_VERSION = "mosVersion";//最新版本信息
    public static final String FUNCODE_QUERY_MOS_CALL_LOG = "queryMosCallLog";//查询通话记录
    public static final String FUNCODE_ADD_MOS_CALL_LOG = "addCallLog";//添加通话记录
    public static final String FUNCODE_QUERY_MOS_CALL_LOG_DETAIL = "queryMosCallLogDetail";//查询通话记录详情
    public static final String FUNCODE_PROCESS_QUERY = "processQuery";//流程查询
    public static final String FUNCODE_HANDLE_LATER = "handle_later";//缓装
    public static final String REPORT_COLUMN_SEQ = "CREATE_DATE";  //统计报表
    public static final String FUNCODE_QUERY_REPORT_LIST="queryReportList";//報表详细列表
    public static final String FUNCODE_ADD_MOS_SURVEY = "addMosSurvey";//MOS回访功能
    public static final String SYS_CONFIG_MOS_STEP_FILTER = "6000013";//MOS回访功能
    public static final String SYS_CONFIG_MOS_RESEND_FAIL_REASON_ID = "6000014";//MOS回访功能
    public static final String SYS_CONFIG_MOS_CREATE_MOS_MSG = "6000015";//是否生成MOS推送信息
    public static final String SYS_CONFIG_MOS_PUSH_SERVER_ADDR = "6000016";//MOS推送信息服务地址
    public static final String SYS_CONFIG_MOS_INIT_CONFIG = "6000020";//MOS推送信息服务地址
    public static final String ENCRYPT_USABLE = "6000025";//数据传输加密控制
    public static final String FUNCODE_MOS_TALK_TO_IOM = "mosTalkToIOM";
    
    public static final String SYS_CONFIG_MOS_QUERY_SYS_CONFIG = "querySysConfig";//取得MOS需要的缓存
    public static final String SYS_CONFIG_MOS_ENCRYPT_CONFIG = "encryptUsable";//取得MOS加密控制数据
    public static final String SYS_CONFIG_FIND_BY_NOW = "findByNow";//取得MOS需要的缓存
    public static final String SYS_CONFIG_QUERY_MOS_SURVEY = "queryMosSurvey";//查询回访信息
    public static final String SYS_CONFIG_ADD_MOS_PICTURE = "addMosPicture";//增加工单详情照片
    public static final String SYS_CONFIG_QUERY_MOS_PICTURE = "queryMosPicture";//查询工单详情照片
    public static final String SYS_CONFIG_DELETE_MOS_PICTURE = "deleteMosPicture";//删除工单详情照片
    public static final String SYS_CONFIG_ADD_STAFF_LOCATION = "addStaffLocation";//添加员工位置
    public static final String SYS_CONFIG_ADD_STAFF_IOM_LOCATION = "addStaffIomLocation";//添加员工位置
    public static final String SYS_CONFIG_INIT_MATERIAL_TYPE = "initMaterialType"; //初始化产品规格信息
    public static final String SYS_CONFIG_QUERY_MATERIAL_TYPE = "queryMaterialType"; //查询产品规格信息
    public static final String SYS_CONFIG_ADD_MATERIAL_TYPE = "addMaterialType"; //增加产品规格信息
    public static final String SYS_CONFIG_UPDATE_MATERIAL_TYPE = "updateMaterialType"; //修改产品规格信息
    public static final String SYS_CONFIG_DELETE_MATERIAL_TYPE = "deleteMaterialType";//删除材料
    public static final String SYS_CONFIG_QUERY_WO_COUNT = "queryWoCount";//查询材料回填表，判断是否回填
    public static final String SYS_CONFIG_WO_HANDLE_INFO = "woHandleInfo"; //查询操记录
    public static final String SYS_CONFIG_WO_RETURN = "woReturn"; //保障单回单
    public static final String SYS_CONFIG_INIT_FETCH_LIST = "initFetchList";//初始化领单列表
    public static final String FUNCODE_QUERY_TERMAIL = "queryDevicesByStaff";//终端设备列表查询
    public static final String FUNCODE_TERMAIL_DETAIL = "queryDeviceByMacAddress";//终端详情
    public static final String FUNCODE_TERMAIL_ADD = "maintainDeviceStaffRelation";//终端领取
    public static final String FUNCODE_TERMAIL_DELETE = "releaseDeviceStaffRelation";//退还终端
    public static final String FUNCODE_QUERY_DEVICE_BY_ACC_NBR = "queryDevicesByAccNbr";//资源核查获取核查资源的列表
    public static final String FUNCODE_QUERY_FUNC_DEVICE_BY_ACC_NBR = "queryResourceByServiceAreaAndType";
    public static final String FUNCODE_QUERY_FUNC_DEVICE_BY_NAME = "queryResourceByNameAndType";
    public static final String FUNCODE_UPLOAD_DEVICE_IMAGE = "uploadDeviceImage";//上传资源图片
    public static final String FUNCODE_DELETE_DEVICE_IMAGE = "deleteImage";//上传资源图片
    public static final String FUNCODE_QUERY_DEVICE_IMAGE_BY_ID = "queryDeviceImageById";//根据资源ID查询图片
    public static final String FUNCODE_CHECKED_RESULT_WARE_HOUSING = "checkedResultWarehousing";//坐标收集
    public static final String FUNCODE_QUERY_DICTIONARY_BY_PARAMETER = "queryDictionaryByParameter";//字典表查询
    public static final String FUNCODE_RES_QUERY =  "resQuery";//资源查询
    public static final String FUNCODE_TERMINAL_CHANGE = "changeTerminal";
    public static final String FUNCODE_MAINTAIN_DEVICE_STAFF_RELATION = "maintainDeviceStaffRelation";//人员领用终端设备
    public static final String FUNCODE_RELEASE_DEVICE_STAFF_RELATION = "releaseDeviceStaffRelation";//解绑人员终端设备关系
    public static final String FUNCODE_MODIFY_RES_STS = "modifyResSts";//修改资源状态
    public static final String FUNCODE_DEVICE_QUERY = "deviceQuery";//资源查询
    
    //资源字典表查询
    public static final String FACTORY = "factory";
    public static final String DEVICE_MODE = "deviceMode";
    public static final String DEVICE_TREATY = "deviceTreaty";
    
    
    
  //  public static final String SYS_CONFIG_MOS_SERVER_ADDR_FOR_ACTION = "6000005";//MOS系统服务端webservice地址
    public static final String MOS_SERVER_ADDR_FOR_ACTION = "http://10.93.38.34:8080/trms/services/RmsForMosService";//MOS系统服务端webservice地址
//    public static final String MOS_SELF_SERVER_ADDR_FOR_ACTION  = "http://10.93.38.34:8080/web_mos/services/";//Mos系统服务端调用自己的webservice地址
//    public static final String MOS_SERVER_ADDR_FOR_ACTION = "http://10.93.38.77:8080/trms/services/RmsForMosService";//MOS系统服务端webservice地址
    public static final String MOS_SELF_SERVER_ADDR_FOR_ACTION  = "http://10.93.38.55:8080/web_mos/services/";//Mos系统服务端调用自己的webservice地址
    public static final String MOS_SERVICE_ADDR_FOR_RMS = "6000006";//Mos系统服务端调用资源2的webservice地址
    public static final String MOS_SYS = "MOS";
    public static final String RETURN_SYS = "returnSys";
    
    public static final String SYS_CONFIG_WO_RUN_STS_FOR_D="6000011";//工单运行状态
    public static final String SYS_CONFIG_WO_STEP_ID="6000012";//工单环节
    
    public static final String SYS_CONFIG_MOS_RES_PRPTY = "6000018";//工单详情中的资源名称
    
    public static final String MOS_RES_PRPTY = "2000000,2000001,3000088,3000025,3000027,3000028,3000010,3000012,3000002,3000004,3000080,3000095,3000096,3000093,3000094,3000062,3000064,2000014,2000015,2000021,2000220,3000081,4000014,4000015";;//工单详情中的资源名称


  //台账接口标识
    public static final String FUNCODE_TZ_QUERY_DEVICE = "queryDevices";//台账设备列表查询
    public static final String FUNCODE_TZ_QUERY_PHOTO = "queryPhoto";//台账设备图片查询
    public static final String FUNCODE_TZ_QUERY_SINGLE_PHOTO = "querySinglePhoto";//台账设备单张图片查询
    public static final String FUNCODE_TZ_ADD_DEVICE_INFO = "AddTZDeviceInfo";//添加设备台账信息，包括基本信息和图片
    public static final String FUNCODE_TZ_DELETE_DEVICE_INFO = "deleteTZDeviceInfo";//删除设备台账信息，包括基本信息和图片
    public static final String FUNCODE_TZ_UPDATE_DEVICE_INFO = "updateTZDeviceInfo";//修改设备台账信息，包括基本信息和图片
    public static final String FUNCODE_TZ_CACHE_INFO_IN_LOGIN = "cacheInfoInLogin";//登录成功后需要缓存的信息
    public static final String FUNC_TZ_FUZZY_QUERY="funcTzFuzzyQuery";//模糊查询
}
