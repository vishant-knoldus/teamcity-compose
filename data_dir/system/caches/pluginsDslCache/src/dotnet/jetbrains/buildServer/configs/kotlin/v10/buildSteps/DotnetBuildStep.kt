package jetbrains.buildServer.configs.kotlin.v10.buildSteps

import jetbrains.buildServer.configs.kotlin.v10.*

/**
 * A [dotnet build step](https://github.com/JetBrains/teamcity-dotnet-plugin) to run .NET CLI command
 * 
 * @see dotnetBuild
 */
open class DotnetBuildStep : BuildStep {
    constructor(init: DotnetBuildStep.() -> Unit = {}, base: DotnetBuildStep? = null): super(base = base as BuildStep?) {
        type = "dotnet"
        param("command", "build")
        init()
    }

    /**
     * Specify paths to projects and solutions. Wildcards are supported.
     */
    var projects by stringParameter("paths")

    /**
     * [Build working directory](https://www.jetbrains.com/help/teamcity/?Build+Working+Directory) for
     * script,
     * specify it if it is different from the [checkout
     * directory](https://www.jetbrains.com/help/teamcity/?Build+Checkout+Directory).
     */
    var workingDir by stringParameter("teamcity.build.workingDir")

    /**
     * Target framework to build for.
     */
    var framework by stringParameter()

    /**
     * Target configuration to build for.
     */
    var configuration by stringParameter()

    /**
     * Target runtime to build for.
     */
    var runtime by stringParameter()

    /**
     * The directory where to place outputs.
     */
    var outputDir by stringParameter()

    /**
     * Defines the value for the $(VersionSuffix) property in the project.
     */
    var versionSuffix by stringParameter()

    /**
     * Enter additional command line parameters for dotnet build.
     */
    var args by stringParameter()

    /**
     * Specify logging verbosity
     * @see Verbosity
     */
    var logging by enumParameter<Verbosity>("verbosity")

    /**
     * Specifies which Docker image platform will be used to run this build step.
     */
    var dockerImagePlatform by enumParameter<ImagePlatform>("plugin.docker.imagePlatform", mapping = ImagePlatform.mapping)

    /**
     * If enabled, "docker pull [image][dockerImage]" will be run before docker run.
     */
    var dockerPull by booleanParameter("plugin.docker.pull.enabled", trueValue = "true", falseValue = "")

    /**
     * Specifies which Docker image to use for running this build step. I.e. the build step will be run inside specified docker image, using 'docker run' wrapper.
     */
    var dockerImage by stringParameter("plugin.docker.imageId")

    /**
     * Additional docker run command arguments
     */
    var dockerRunParameters by stringParameter("plugin.docker.run.parameters")

    /**
     * Logging verbosity
     */
    enum class Verbosity {
        Quiet,
        Minimal,
        Normal,
        Detailed,
        Diagnostic;

    }
    /**
     * Docker image platforms
     */
    enum class ImagePlatform {
        Any,
        Linux,
        Windows;

        companion object {
            val mapping = mapOf<ImagePlatform, String>(Any to "", Linux to "linux", Windows to "windows")
        }

    }
}


/**
 * Adds a [dotnet build step](https://github.com/JetBrains/teamcity-dotnet-plugin) to run .NET CLI command
 * @see DotnetBuildStep
 */
fun BuildSteps.dotnetBuild(base: DotnetBuildStep? = null, init: DotnetBuildStep.() -> Unit = {}) {
    step(DotnetBuildStep(init, base))
}
