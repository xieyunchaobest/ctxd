<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl" language="JavaScript">

<xsl:template match="tree">
	<xsl:apply-templates select="entity"/>
</xsl:template>

<xsl:template match="entity">
	<div onClick="window.event.cancelBubble=true;clickOnEntity(this);" onSelectStart="return false" onDragStart="return false">
		<xsl:attribute name="image"><xsl:value-of select="image"/></xsl:attribute>
		<xsl:attribute name="imageOpen"><xsl:value-of select="imageOpen"/></xsl:attribute>
		<xsl:attribute name="open">false</xsl:attribute>
		<xsl:attribute name="leaf"><xsl:value-of select="leaf"/></xsl:attribute>
		<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
		<xsl:attribute name="STYLE">
			padding-left: 20px;
			cursor: hand;
			<xsl:if expr="depth(this)>2">display: none</xsl:if>
		</xsl:attribute>
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="middle">
					<img border="0" id="image">
						<xsl:attribute name="SRC">
							<xsl:value-of select="image"/>
						</xsl:attribute>
					</img>
				</td>
				<td valign="middle" nowrap="true">
					<xsl:attribute name="STYLE">
						padding-left: 7px;
						font-family: Verdana;
						font-size: 11px;
						font-color: black;

					</xsl:attribute>
					<xsl:value-of select="description"/>
				</td>
			</tr>
		</table>
		<xsl:apply-templates select="contents/entity"/>
	</div>
</xsl:template>

</xsl:stylesheet>
