<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <xsl:include href="common.xsl"/>

    <xsl:template name="leftPane">
        <xsl:apply-templates select="page/data[@id='userQuery']" mode="show"/>
    </xsl:template>

    <xsl:template match="page/data[@id='userQuery']" mode="show">
        <h2 style="text-align:center;">Рейтинг</h2>
        <p align="center">
        <!-- пока так  -->
            <table>
            <xsl:for-each select=".">
                <tr>
                    <td>
                        <xsl:value-of select="."/>
                    </td>
                    <td>
                        <xsl:value-of select="."/>
                    </td>
                    <td>
                        <xsl:value-of select="."/>
                    </td>
                </tr>
            </xsl:for-each>
            </table>
        </p>
    </xsl:template>

</xsl:stylesheet>