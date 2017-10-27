1. To secure the grid in the setEnv.bat do:
    set EXT_JAVA_OPTIONS=%EXT_JAVA_OPTIONS% -Dcom.gs.security.enabled=true
2. In the gs_logging.properties file:
	add:
	com.gigaspaces.security.audit.enabled = true
	com.gigaspaces.security.audit.level = FINE
	com.gigaspaces.security.audit.handler = com.gigaspaces.security.audit.AuditHandler
	
3. Run the GigaSpaces Agent from the start menu of from [GigaSpacesRoot]\Bin\Gs-agent.bat.
4. Run the GigaSpaces Management Center from the start menu or from [GigaSpacesRoot]\Bin\Gs-ui.bat.
5. Create new user kuku/1234.
6. Import project using eclipse and run the main.
7. Change group/locators;
8. Follow the GSC logs for auditing through GigaSpaces Management Center.

For more information on the auditing please look:
http://docs.gigaspaces.com/xap97/auditing.html
