<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <!--<xsl:include href="common.xsl"/>-->


<xsl:template  match="/">

        <html>
            <head>
                <title>Рейтинг</title>
            </head>
            <body>
                <div class="main">
                    <div class="header">
                        <div class="menu">
                        </div>
                    </div>
                    <div class="content">
                        <div id="leftPane">
                            <xsl:call-template name="leftPane"/>
                        </div>
                    </div>
                </div>
            </body>
        </html>

    </xsl:template>

    <xsl:template name="leftPane">
        <xsl:apply-templates select="page/data[@id='userQuery']" mode="show"/>
    </xsl:template>

    <xsl:template match="page/data[@id='userQuery']" mode="show">
        <h2 style="text-align:center;">Рейтинг c topcoder'a</h2>
        <p align="center">
        <!-- пока так  -->

            <table>
                <!--DBResponse - нужный класс, rating и city - его поля-->
            <xsl:for-each select="d-b-response">
                <tr>
                    <td>
                        <xsl:value-of select="rating"/>
                    </td>
                    <td>
                        <xsl:value-of select="city"/>
                    </td>
                </tr>
            </xsl:for-each>
            </table>
        </p>
    </xsl:template>

</xsl:stylesheet>