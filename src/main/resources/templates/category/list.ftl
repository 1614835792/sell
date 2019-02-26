<html>
<#include "../common/header.ftl">
 <body>
 <div id="wrapper" class="toggled">
       <#--边栏sidebar-->
        <#include "../common/nav.ftl">
       <#--主要内容-->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th>类目id</th>
                                <th>名字</th>
                                <th>type</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                <#list categoryList as category>
                <tr>
                    <td>${category.categoryId}<br></td>
                    <td>${category.categoryName}<br></td>
                    <td>${category.categoryType}<br></td>
                    <td>${category.createTime}<br></td>
                    <td>${category.updateTime}<br></td>
                    <td>
                         <#if (category.categoryType)??>
                             <a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a><br>
                         </#if>
                    </td>
                </tr>
                </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
 </div>

 </body>
</html>
