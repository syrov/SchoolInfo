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

        </p>

        <p align="center">
           <div class="div-table" id="rtable">
                <!--DBResponse - нужный класс, rating и city - его поля-->
               <xsl:for-each select="dbresponse">
                   <!--TODO: средствами xsl или javascript сделать чередование цвета строк -->
                   <div class="div-tr">
                      <!--<td>-->
                          <!--<xsl:value-of select="@rating"/>-->
                      <!--</td>-->
                      <div class="div-td">
                          <xsl:element name="{name()}">
                            <xsl:value-of select="position()"/>
                          </xsl:element>.
                      </div>
                      <div class="div-td">
                          <xsl:copy-of select="name/text()"/>
                      </div>
                      <div class="div-td" style="border-right:0px solid black;">
                          <xsl:copy-of select="city/text()"/>
                      </div>
                    </div>
              </xsl:for-each>
            </div>
        </p>

    </xsl:template>

</xsl:stylesheet>

