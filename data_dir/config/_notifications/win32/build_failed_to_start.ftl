<#-- Uses FreeMarker template syntax, template guide can be found at http://freemarker.org/docs/dgui.html -->

<#import "common.ftl" as common>

<#global link>${link.buildResultsLink}</#global>
<#global message>${project.fullName} / ${buildType.name} <@common.short_build_info build/> failed to start ${var.buildShortStatusDescription}<#if !build.agentLessBuild> on ${agentName}</#if></#global>
