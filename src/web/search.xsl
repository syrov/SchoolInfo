<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <xsl:include href="common.xsl"/>
             <!--

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

        -->
    <xsl:template name="leftPane">
        <xsl:apply-templates select="page/data[@id='userQuery']" mode="show"/>
    </xsl:template>

    <xsl:template match="collection" mode="show">
        <h2 style="text-align:center;">Рейтинг по итогам TopCoder.</h2>
        <p align="center">
           <table>
                <!--DBResponse - нужный класс, rating и city - его поля-->
           <xsl:for-each select="dbresponse">
               <!--TODO: заменить таблицу на div'ы, чтобы потом сделать их кликабельными -->
               <!--TODO: средствами xsl или javascript сделать чередование цвета строк -->
               <tr>
                  <!--<td>-->
                      <!--<xsl:value-of select="@rating"/>-->
                  <!--</td>-->
                  <td>
                      <xsl:copy-of select="name/text()"/>
                  </td>
                  <td style="border-right:0px solid black;">
                      <xsl:copy-of select="city/text()"/>
                  </td>
              </tr>
          </xsl:for-each>
            </table>
        </p>
    </xsl:template>

</xsl:stylesheet>