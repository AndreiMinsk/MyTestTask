<div id="search">

    <g:form url='[controller: "client", action: "search"]'
            id="clientSearchForm"
            name="clientSearchForm"
            method="get">
        <g:textField name='q' value='${params.q}'   />


        <input type="submit" value="Find a client"  />

    </g:form>
</div>
