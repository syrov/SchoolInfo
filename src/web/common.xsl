<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>


    <xsl:template match="/">

        <html>
            <head>
                <title>School Info.</title>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <link rel="stylesheet" type="text/css" href="styles.css"/>
            </head>
            <body>
                <div class="main">
                    <div class="header">
                        <div class="menu">
                            <div style="height: inherit;display:table-cell; vertical-align:middle;">
                                <img src="SI_cap.gif" height="20px"/>
                                SchoolInfo
                            </div>
                        </div>
                    </div>
                    <div class="content">
                        <div id="leftPane">
                            <xsl:call-template name="leftPane"/>
                        </div>
                        <div id="rightPane">
                            <form action="search.xml" method="get">
                                <br/>Направление
                                <br/>
                                <xsl:call-template name="dir"/>

                                <br/>
                                <br/>Специальность
                                <br/>
                                <xsl:call-template name="spec"/>
                                <br/>
                                <br/>Город
                                <br/>
                                <xsl:call-template name="city"/>

                                <br/>
                                <br/>Университет
                                <br/>
                                <xsl:call-template name="uni"/>

                                <br/>
                                <br/>
                                <p style="text-align:center;">
                                    <input type="submit" value="Найти"/>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </body>
        </html>

    </xsl:template>

    <xsl:template name="dir">
        <select name="_dir">
                <option value="1">one</option>
                <option value="2">two</option>
                <option value="3">three</option>
        </select>
    </xsl:template>

    <xsl:template name="spec">
        <select name="_spec">
                <option value="1">one</option>
                <option value="2">two</option>
                <option value="3">three</option>
        </select>
    </xsl:template>

    <xsl:template name="uni">
        <select name="_uni">
                <option value="1">one</option>
                <option value="2">two</option>
                <option value="3">three</option>
        </select>
    </xsl:template>

    <xsl:template name="city">
        <select name="_city">
                <option value="1">one</option>
                <option value="2">two</option>
                <option value="3">three</option>
        </select>
    </xsl:template>


</xsl:stylesheet>
