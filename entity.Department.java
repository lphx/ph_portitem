package FreeMarker template error (DEBUG mode; use RETHROW in production!):
The following has evaluated to null or missing:
==> entityPackage  [in template "entity.ftl" at line 1, column 11]

----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${entityPackage}  [in template "entity.ftl" at line 1, column 9]
----

Java stack trace (for programmers):
----
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134)
	at freemarker.core.EvalUtil.coerceModelToTextualCommon(EvalUtil.java:467)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:389)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:358)
	at freemarker.core.DollarVariable.calculateInterpolatedStringOrMarkup(DollarVariable.java:100)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:63)
	at freemarker.core.Environment.visit(Environment.java:330)
	at freemarker.core.Environment.visit(Environment.java:336)
	at freemarker.core.Environment.process(Environment.java:309)
	at freemarker.template.Template.process(Template.java:384)
	at cn.phlos.port.item.sql.engine.FreemarkerTemplateEngine.write(FreemarkerTemplateEngine.java:32)
	at cn.phlos.port.item.sql.builder.ConfigBuilder.shiti(ConfigBuilder.java:28)
	at cn.phlos.port.item.sql.Generate.main(Generate.java:61)
