document.writeln("<aside class=\'lyear-layout-sidebar\'  >");
document.writeln("    <!-- logo -->");
document.writeln("    <div id=\'logo\' class=\'sidebar-header\'>");
document.writeln("        <a  href=\'index.html\'><h2>AVG管理系统</h2></a>");
document.writeln("    </div>");
document.writeln("    <div class=\'lyear-layout-sidebar-scroll\'>");
document.writeln("      <nav class=\'sidebar-main\'>");
document.writeln("            <ul class=\'nav nav-drawer\'>");
document.writeln("                <li class=\'nav-item nav-item-has-subnav\'>");
document.writeln("                    <a   href=\'javascript:void(0)\'><i class=\'mdi mdi-palette\'></i>AVG管理菜单</a>");
document.writeln("                    <ul class=\'nav nav-subnav\'>");
/*
// document.writeln("                        <li> <a   href=\'/equipmentStatusMonitoring\'>设备状态监控</a> </li>");
// document.writeln("                        <li> <a   href=\'/ioequipmentStatusMonitoring\'>设备IO状态监控</a> </li>");
// document.writeln("                        <li> <a   href=\'/safetyMonitoring\'>设备操作监控</a> </li>");
// document.writeln("                        <li> <a   href=\'/curveQuery\'>压装曲线监控</a> </li>");
document.writeln("                        <li> <a   href=\'/temperatureIndex\'>温度监控</a> </li>");
document.writeln("                        <li> <a   href=\'/temperature\'>温度实时监控</a> </li>");
document.writeln("                        <li> <a   href=\'/hisTemperatureQuery\'>温度曲线</a> </li>");
document.writeln("                        <li> <a   href=\'/temperatureHisAlarm\'>历史温度报警信息</a> </li>");
document.writeln("                        <li> <a   href=\'/hisTemperature\'>历史温度报表</a> </li>");
// document.writeln("                        <li> <a   href=\'/temperatureSetting\'>温度警戒值设置</a> </li>");
 */

// document.writeln("                        <li> <a   href=\'/temperatureConfig\'>系统配置</a> </li>");


// document.writeln("                        <li> <a   href=\'/hyperbola\'>双曲线</a> </li>");
document.writeln("                        <li> <a   href=\'/avgBoard\'>看板</a> </li>");
document.writeln("                        <li> <a   href=\'/factoryInfo\'>工厂管理</a> </li>");
document.writeln("                        <li> <a   href=\'/mapInfo\'>地图管理</a> </li>");
document.writeln("                        <li> <a   href=\'/avgManager\'>AVG管理</a> </li>");
document.writeln("                        <li> <a   href=\'/avgDbConfig\'>数据库管理</a> </li>");
document.writeln("                        <li> <a   href=\'/communicateInfo\'>通讯管理</a> </li>");


// document.writeln("                        <li> <a   href=\'/avgConfig\'>AVG配置信息</a> </li>");
// document.writeln("                        <li> <a   href=\'/avgConfigAdd\'>添加AVG</a> </li>");
// document.writeln("                        <li> <a   href=\'/mapInfo\'>地图</a> </li>");
// document.writeln("                        <li> <a   href=\'/avgInfoList\'>AVG小车信息列表</a> </li>");
// document.writeln("                        <li> <a   href=\'/avgDbConfig\'>AVG数据库配置信息</a> </li>");
// document.writeln("                        <li> <a   href=\'/avgInfoList\'>添加AVG</a> </li>");
// document.writeln("                        <li> <a   href=\'/avgDbAdd\'>添加AVG数据库配置</a> </li>");
// document.writeln("                        <li> <a   href=\'/temperaturePlcConfig\'>PLC连接信息</a> </li>");
// document.writeln("                        <li> <a   href=\'/temperaturePlcConfigAdd\'>添加PLC连接信息</a> </li>");
// document.writeln("                        <li> <a   href=\'/avgInfoList\'>AVG小车信息列表</a> </li>");
// document.writeln("                        <li> <a   href=\'/temperaturePlcConfigAdd\'>通讯管理</a> </li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
// document.writeln("                <li class=\'nav-item nav-item-has-subnav\'>");
// document.writeln("                    <a  href=\'javascript:void(0)\'><i class=\'mdi mdi-format-align-justify\'></i>系统操作与设置</a>");
// document.writeln("                    <ul class=\'nav nav-subnav\'>");
// document.writeln("                        <li> <a   href=\'/manualOperation\'>手动操作</a> </li>");
// document.writeln("                        <li> <a   href=\'/parameterSetting\'>系统参数设置</a> </li>");
// document.writeln("                    </ul>");
// document.writeln("                </li>");
// document.writeln("                <li class=\'nav-item nav-item-has-subnav \'>");
// document.writeln("                    <a   href=\'javascript:void(0)\'><i class=\'mdi mdi-file-outline\'></i>产品压装程序设置</a>");
// document.writeln("                    <ul class=\'nav nav-subnav\'>");
// // document.writeln("                        <li> <a  href=\'/programMing\'>压装程序编写</a> </li>");
// document.writeln("                        <li> <a  href=\'/programMing\'>产品压装程序编写</a> </li>");
// // document.writeln("                        <li> <a  href=\'/parameterStorage\'>产品压装参数存储</a> </li>");
// document.writeln("                    </ul>");
// document.writeln("                </li>");
// document.writeln("                <li class=\'nav-item nav-item-has-subnav\'>");
// document.writeln("                    <a   href=\'javascript:void(0)\'><i class=\'mdi mdi-language-javascript\'></i>历史曲线信息</a>");
// document.writeln("                    <ul class=\'nav nav-subnav\'>");
// // document.writeln("                        <li> <a  href=\'/curveAcquisition\'>曲线采集</a> </li>");
// // document.writeln("                        <li> <a  href=\'/curveStorage\'>曲线存储</a> </li>");
// document.writeln("                        <li> <a  href=\'/dataQuery\'>历史汇总数据查询</a> </li>");
// // document.writeln("                        <li> <a  href=\'/hisTemperatureQuery\'>历史曲线查询</a> </li>");
// document.writeln("                        <li> <a  href=\'/dataExport\'>历史数据导出</a> </li>");
// document.writeln("                    </ul>");
// document.writeln("                </li>");
// document.writeln("                <li class=\'nav-item nav-item-has-subnav\'>");
// document.writeln("                    <a   href=\'javascript:void(0)\'><i class=\'mdi mdi-menu\'></i>账户管理</a>");
// document.writeln("                    <ul class=\'nav nav-subnav\'>");
// document.writeln("                        <li  > <a  href=\'/accountManagement\'>账户管理</a> </li>");
// // document.writeln("                        <li > <a  href=\'/OperatorRole\'>修改账户</a> </li>");
// document.writeln("                        <li> <a  href=\'/createAnAccount\'>新建账号</a> </li>");
// document.writeln("                    </ul>");
// document.writeln("                </li>");
// document.writeln("                <li class=\'nav-item nav-item-has-subnav\'>");
// document.writeln("                    <a   href=\'javascript:void(0)\'><i class=\'mdi mdi-menu\'></i> 报警</a>");
// document.writeln("                    <ul class=\'nav nav-subnav\'>");
// document.writeln("                        <li> <a   href=\'/equipmentFaultAlarm\'>实时报警信息</a> </li>");
// document.writeln("                        <li> <a   href=\'/hisAlarm\'>历史报警信息</a> </li>");
// document.writeln("                    </ul>");
// document.writeln("                </li>");
// document.writeln("                <li class=\'nav-item nav-item-has-subnav\'>");
// document.writeln("                    <a  href=\'javascript:void(0)\'><i class=\'mdi mdi-menu\'></i> PLC对接</a>");
// document.writeln("                    <ul class=\'nav nav-subnav\'>");
// document.writeln("                        <li> <a   href=\'\'>多型号对接支持</a> </li>");
// document.writeln("                    </ul>");
// document.writeln("                </li>");
document.writeln("            </ul>");
document.writeln("        </nav>");
document.writeln("    </div>");
document.writeln("</aside>");
document.writeln("<!--End 左侧导航-->");
document.writeln("<!--头部信息-->");
document.writeln("<div style=\'margin-bottom: 100px;\'>");
document.writeln("<header class=\'lyear-layout-header\'>");
document.writeln("    <nav class=\'navbar navbar-default\'>");
document.writeln("        <div class=\'topbar\'>");
document.writeln("");
document.writeln("            <div class=\'topbar-left\'>");
document.writeln("                <div class=\'lyear-aside-toggler\'>");
document.writeln("                    <span class=\'lyear-toggler-bar\'></span>");
document.writeln("                    <span class=\'lyear-toggler-bar\'></span>");
document.writeln("                    <span class=\'lyear-toggler-bar\'></span>");
document.writeln("                </div>");
document.writeln("                <span class=\'navbar-page-title\'>欢迎你 </span>");
// document.writeln("<img src=\"images/logo/rzd.png\">");
document.writeln("            </div>");
document.writeln("");
document.writeln("            <ul class=\'topbar-right\'>");
document.writeln("                <li class=\'dropdown dropdown-profile\'>");
document.writeln("                    <a href=\'javascript:void(0)\' data-toggle=\'dropdown\'>");
document.writeln("");
document.writeln("                        <span>我的<span class=\'caret\'></span></span>");
document.writeln("                    </a>");
document.writeln("                    <ul class=\'dropdown-menu dropdown-menu-right\'>");
document.writeln("                        <li> <a  href=\'lyear_pages_profile.html\'><i class=\'mdi mdi-account\'></i> 个人信息</a> </li>");
document.writeln("                        <li> <a  href=\'lyear_pages_edit_pwd.html\'><i class=\'mdi mdi-lock-outline\'></i> 修改密码</a> </li>");
document.writeln("                        <li class=\'divider\'></li>");
document.writeln("                        <li> <a   href=\'lyear_pages_login.html\'><i class=\'mdi mdi-logout-variant\'></i> 退出登录</a> </li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
document.writeln("                <!--切换主题配色-->");
document.writeln("                <li class=\'dropdown dropdown-skin\'>");
document.writeln("                    <span data-toggle=\'dropdown\' class=\'icon-palette\'>切换主题配色</span>");
document.writeln("                    <ul class=\'dropdown-menu dropdown-menu-right\' data-stopPropagation=\'true\'>");
document.writeln("                        <li class=\'drop-title\'><p>主题</p></li>");
document.writeln("                        <li class=\'drop-skin-li clearfix\'>");
document.writeln("                                            <span class=\'inverse\'>");
document.writeln("                                                <input type=\'radio\' name=\'site_theme\' value=\'default\' id=\'site_theme_1\' checked>");
document.writeln("                                                <label for=\'site_theme_1\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'site_theme\' value=\'dark\' id=\'site_theme_2\'>");
document.writeln("                                                <label for=\'site_theme_2\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'site_theme\' value=\'translucent\' id=\'site_theme_3\'>");
document.writeln("                                                <label for=\'site_theme_3\'></label>");
document.writeln("                                            </span>");
document.writeln("                        </li>");
document.writeln("                        <li class=\'drop-title\'><p>LOGO</p></li>");
document.writeln("                        <li class=\'drop-skin-li clearfix\'>");
document.writeln("                                            <span class=\'inverse\'>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'default\' id=\'logo_bg_1\' checked>");
document.writeln("                                                <label for=\'logo_bg_1\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'color_2\' id=\'logo_bg_2\'>");
document.writeln("                                                <label for=\'logo_bg_2\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'color_3\' id=\'logo_bg_3\'>");
document.writeln("                                                <label for=\'logo_bg_3\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'color_4\' id=\'logo_bg_4\'>");
document.writeln("                                                <label for=\'logo_bg_4\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'color_5\' id=\'logo_bg_5\'>");
document.writeln("                                                <label for=\'logo_bg_5\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'color_6\' id=\'logo_bg_6\'>");
document.writeln("                                                <label for=\'logo_bg_6\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'color_7\' id=\'logo_bg_7\'>");
document.writeln("                                                <label for=\'logo_bg_7\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'logo_bg\' value=\'color_8\' id=\'logo_bg_8\'>");
document.writeln("                                                <label for=\'logo_bg_8\'></label>");
document.writeln("                                            </span>");
document.writeln("                        </li>");
document.writeln("                        <li class=\'drop-title\'><p>头部</p></li>");
document.writeln("                        <li class=\'drop-skin-li clearfix\'>");
document.writeln("                                            <span class=\'inverse\'>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'default\' id=\'header_bg_1\' checked>");
document.writeln("                                                <label for=\'header_bg_1\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'color_2\' id=\'header_bg_2\'>");
document.writeln("                                                <label for=\'header_bg_2\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'color_3\' id=\'header_bg_3\'>");
document.writeln("                                                <label for=\'header_bg_3\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'color_4\' id=\'header_bg_4\'>");
document.writeln("                                                <label for=\'header_bg_4\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'color_5\' id=\'header_bg_5\'>");
document.writeln("                                                <label for=\'header_bg_5\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'color_6\' id=\'header_bg_6\'>");
document.writeln("                                                <label for=\'header_bg_6\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'color_7\' id=\'header_bg_7\'>");
document.writeln("                                                <label for=\'header_bg_7\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'header_bg\' value=\'color_8\' id=\'header_bg_8\'>");
document.writeln("                                                <label for=\'header_bg_8\'></label>");
document.writeln("                                            </span>");
document.writeln("                        </li>");
document.writeln("                        <li class=\'drop-title\'><p>侧边栏</p></li>");
document.writeln("                        <li class=\'drop-skin-li clearfix\'>");
document.writeln("                                            <span class=\'inverse\'>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'default\' id=\'sidebar_bg_1\' checked>");
document.writeln("                                                <label for=\'sidebar_bg_1\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'color_2\' id=\'sidebar_bg_2\'>");
document.writeln("                                                <label for=\'sidebar_bg_2\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'color_3\' id=\'sidebar_bg_3\'>");
document.writeln("                                                <label for=\'sidebar_bg_3\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'color_4\' id=\'sidebar_bg_4\'>");
document.writeln("                                                <label for=\'sidebar_bg_4\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'color_5\' id=\'sidebar_bg_5\'>");
document.writeln("                                                <label for=\'sidebar_bg_5\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'color_6\' id=\'sidebar_bg_6\'>");
document.writeln("                                                <label for=\'sidebar_bg_6\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'color_7\' id=\'sidebar_bg_7\'>");
document.writeln("                                                <label for=\'sidebar_bg_7\'></label>");
document.writeln("                                            </span>");
document.writeln("                            <span>");
document.writeln("                                                <input type=\'radio\' name=\'sidebar_bg\' value=\'color_8\' id=\'sidebar_bg_8\'>");
document.writeln("                                                <label for=\'sidebar_bg_8\'></label>");
document.writeln("                                            </span>");
document.writeln("                        </li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
document.writeln("                <!--切换主题配色-->");
document.writeln("            </ul>");
document.writeln("");
document.writeln("        </div>");
document.writeln("    </nav>");
document.writeln("</header>");
document.writeln("            </div>");
window.onload=function loade() {
    var url=window.location.pathname;
    $("[href='"+url+"']").parent().parent().parent().addClass("nav-item nav-item-has-subnav active open");
    $("[href='"+url+"']").parent().addClass("active");
}
