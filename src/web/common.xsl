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
                                <img src="images/SI_cap.gif" height="20px"/>
                                SchoolInfo
                            </div>
                        </div>
                    </div>
                    <div class="content">
                        <div id="leftPane">
                            <h2 style="text-align:center;">Поступление в России - это игра</h2>
                            по правилам ВУЗов и Министерства Образования.
                            <br/>
                            <br/>
                            <b>Цель:</b>
                            <br/>
                            взять на прицел лучший по твоему мнению ВУЗ и поступить в него.
                            <br/>
                            <b>Средство:</b>
                            <br/>данные о конкурсах прошлых лет, проходных баллах, заслугаах и трудоустроенности
                            выпускников и прочее.
                            <br/>
                            <br/>
                            Всё это мы поможем тебе найти и оценить.
                            <br/>
                            Ставка - твоё будущее.
                            <br/>
                            <b>Дерзай!</b>
                            <br/>
                            <!--Поступление - это бой -->
                            <!--Поступление - это экзамен -->
                        </div>
                        <div id="rightPane">
                            <form method="post">
                                <!--<br/>Направление-->
                                <!--<br/>-->
                                <!--<xsl:call-template name="_dir"/>-->

                                <!--<br/>-->
                                <!--<br/>Специальность-->
                                <!--<br/>-->
                                <!--&lt;!&ndash;-->
                                <!--<xsl:call-template name="dir"/>-->
                                <!--&ndash;&gt;-->
                                <!--<br/>-->
                                <!--<br/>Город-->
                                <!--<br/>-->
                                <!--<xsl:call-template name="_city"/>-->

                                <br/>
                                <br/>Университет
                                <br/>
                                <xsl:call-template name="_uni"/>

                                <!--<br/>-->
                                <!--<br/>-->
                                <!--<p style="text-align:center;">-->
                                    <!--<input type="button" value="Найти"/>-->
                                <!--</p>-->
                            </form>
                        </div>
                    </div>
                </div>
            </body>
        </html>

    </xsl:template>

    <!--<xsl:template name="_dir">-->
        <!--<select name="ComboBox">-->
            <!--<option>-->
                <!--<xsl:apply-template name="dir"/>-->
            <!--</option>-->
        <!--</select>-->
    <!--</xsl:template>-->

    <!--<xsl:template name="_spec">-->
        <!--<select name="ComboBox">-->
            <!--<option>-->
                <!--<xsl:apply-template name="spec"/>-->
            <!--</option>-->
        <!--</select>-->
    <!--</xsl:template>-->

    <xsl:template name="_uni">
        <!--<form method="get" action="/index.xml" align="center">-->
                    <!--<input class="xlarge" type="text" name="_uni" size="50"/>-->
                    <!--<input class="btn primary" type="submit" value="поиск"/>-->
                <!--</form>-->
        <select name="ComboBox">
            <xsl:apply-template name="_uni"/>
        </select>
    </xsl:template>

    <!--<xsl:template name="_city">-->
        <!--<select name="ComboBox">-->
            <!--<xsl:apply-template name="city"/>-->
        <!--</select>-->
    <!--</xsl:template>-->

    <!--
    <xsl:template name="find">
         <select name="ComboBox" >
            <option value="0"></option>
         </select>
    </xsl:template>
    -->

</xsl:stylesheet>
