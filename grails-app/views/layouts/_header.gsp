<g:if test="${session?.user}">
    <div>
        <g:render template="/layouts/clientSearch" />
        <div id = "zz">
            <g:render template="/layouts/upload"/>
        </div>
    </div>
</g:if>
<div id="header">
<p><div id="headerMain">
            Task

    </div>
<div id="loginHeader">
<g:loginControl />


</div>


</p>
</div>