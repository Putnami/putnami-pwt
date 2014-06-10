Putnami Web Toolkit
===================

Putnami Web Toolkit is a [GWT](http://www.gwtproject.org/) based framework, providing nice graphical components (based on [Bootstrap](http://www.getbootstrap.com/)), and a bunch of nice features like data binding, unified server calls, etc.

The **main goal** of this framework is to improve your productivity by removing unnecessary, useless code. The framework will then generate what's necessary.

# Resources
Useful links :

* [Documentation](http://pwt.putnami.org/)
* [Getting started with the framework](http://pwt.putnami.org/#GettingStarted)
* [Release repository](http://repo.putnami.org/)
* [Download page](http://pwt.putnami.org/download.html)

# License
The framework is delivered under LGPL v 3.0.

This license allows the use of PWT in both open source and commercial projects, and guarantees that this framework and any modification made to it will stay open source.

The use of PWT in your application doesn't and will not affect the license of you application. Using PWT is free of charge and you won't have to pay any fees to use or integrate it.

You can find a [copy](https://github.com/Putnami/putnami-pwt/raw/master/COPYING) of the GPL v3 or you can get the [original](http://www.gnu.org/licenses/gpl-3.0.txt) and a [copy](https://github.com/Putnami/putnami-pwt/raw/master/COPYING.LESSER) of the LGPL v3 or you can get the [orignal](https://www.gnu.org/licenses/lgpl-3.0.txt).

# Usage #
To use this framework, simply add a dependency to the core jar (*pwt-core.jar*) and eventually to the needed plugin jars (*pwt-code-editor.jar* and/or *pwt-google-analytics.jar*).

We release those jar on our maven repository hosted on github reachable via the following URL : http://repo.putnami.org

We provide two sample apps in this repo in the [samples](https://github.com/Putnami/putnami-pwt/tree/master/samples) folder :

* One is a GWT library using PWT
* One is a GWT Web application using PWT

In each project, you'll find gradle and maven build files.

## Maven usage

Add our maven repository (or install the artifacts in yours) :
```xml
<repositories>
    <repository>
        <id>putnami.org</id>
        <url>http://repo.putnami.org/</url>
    </repository>
</repositories>
```

Set the needed properties
```xml
<properties>
    ...
    <gwt.version>2.6.1</gwt.version>
    <pwt.version>1.0.0-RC1</pwt.version>
    <project.compiler.source>1.7</project.compiler.source>
    <project.compiler.target>1.7</project.compiler.target>
    <project.sourceEncoding>UTF-8</project.sourceEncoding>
    ...
</properties>
```

Then add the desired dependencies as well as  GWT's
```xml
<dependencies>
	...
	<dependency>
		<groupId>fr.putnami.pwt</groupId>
		<artifactId>pwt</artifactId>
		<version>${pwt.version}</version>
	</dependency>
    <!-- You may want to use these plugins -->
    <dependency>
        <groupId>fr.putnami.pwt</groupId>
        <artifactId>pwt-code-editor</artifactId>
        <version>${pwt.version}</version>
    </dependency>
    <dependency>
        <groupId>fr.putnami.pwt</groupId>
        <artifactId>pwt-google-analytics</artifactId>
        <version>${pwt.version}</version>
    </dependency>

    <!-- GWT dependencies-->
    <dependency>
        <groupId>com.google.gwt</groupId>
        <artifactId>gwt-user</artifactId>
        <version>${gwt.version}</version>
        <scope>provided</scope>
    </dependency>
	<dependency>
		<groupId>com.google.gwt</groupId>
		<artifactId>gwt-servlet</artifactId>
		<version>${gwt.version}</version>
	</dependency>
	...
</dependencies>
```

Then configure the Maven plugins
```xml
<build>
<pluginManagement>
		<plugins>
            ...
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<source>${project.compiler.source}</source>
					<target>${project.compiler.target}</target>
					<encoding>${project.sourceEncoding}</encoding>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>gwt-maven-plugin</artifactId>
				<version>${gwt.version}</version>
				<executions>
					<execution>
						<goals>
							<goal>compile</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
            ...
		</plugins>
	</pluginManagement>
	<plugins>
        ...
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
		</plugin>
		<plugin>
			<groupId>org.codehaus.mojo</groupId>
			<artifactId>gwt-maven-plugin</artifactId>
		</plugin>
        ...
	</plugins>
</build>
```
### Library usage
Optionally add the gwt-dev dependency if needed
```xml
<dependency>
    <groupId>com.google.gwt</groupId>
    <artifactId>gwt-dev</artifactId>
    <version>${gwt.version}</version>
    <scope>provided</scope>
</dependency>
```

And add the *resources* goal to Maven plugin in order to include java sources files in the generated jar (nedded for GWT compilation)
```xml
...
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>gwt-maven-plugin</artifactId>
    <version>${gwt.version}</version>
    <executions>
        <execution>
            <goals>
                <goal>resources</goal>
                <goal>compile</goal>
            </goals>
        </execution>
    </executions>
</plugin>
...
```

### Web application usage

Set the needed properties
```xml
<properties>
    ...
    <webappDirectory>${project.build.directory}/${project.build.finalName}</webappDirectory>
    ...
</properties>
```

Configure the needed Maven plugins
```xml
<build>
    <outputDirectory>${webappDirectory}/WEB-INF/classes</outputDirectory>
    <pluginManagement>
        <plugins>
            ...
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.4</version>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>exploded</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <webappDirectory>${webappDirectory}</webappDirectory>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>gwt-maven-plugin</artifactId>
                <version>${gwt.version}</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <configuration>
                        <runTarget>... Your host Page if using dev mode ...</runTarget>
                        <modules>
                            <module>... Your module ...</module>
                        </modules>
                    </configuration>
                    <hostedWebapp>${webappDirectory}</hostedWebapp>
                </configuration>
            </plugin>
            ...
        </plugins>
    </pluginManagement>
    <plugins>
        ...
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>gwt-maven-plugin</artifactId>
        </plugin>
        ...
    </plugins>
</build>
```

#### Running in standard DevMode
*Note : the standard DevMode is no more supported on Chrome for Linux or on Firefox*

Use `mvn gwt:run`

#### Runing in SuperDevMode
To run the SuperDevMode you'll need the maven jetty plugin.

Set the needed properties
```xml
<properties>
    ...
    <jetty.plugin.version>9.2.0.v20140526</jetty.plugin.version>
    ...
</properties>
```

Then configure the Maven plugins
```xml
<build>
    <pluginManagement>
        <plugins>
            ...
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>${jetty.plugin.version}</version>
                <configuration>
                    <webAppSourceDirectory>${webappDirectory}</webAppSourceDirectory>
                    <contextPath>/</contextPath>
                </configuration>
            </plugin>
            ...
        </plugins>
    </pluginManagement>
    <plugins>
        ...
        <plugin>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-maven-plugin</artifactId>
        </plugin>
        ...
    </plugins>
</build>
```

To use different GWT module .xml files for development tasks, we use a maven profile

```xml
<profiles>
	<profile>
		<id>dev</id>
		<build>
			<plugins>
				<plugin>
					<groupId>org.codehaus.mojo</groupId>
					<artifactId>gwt-maven-plugin</artifactId>
					<executions>
						<execution>
							<phase>compile</phase>
							<goals>
								<goal>compile</goal>
							</goals>
						</execution>
					</executions>
					<configuration>
						<modules>
							<module>... Your Dev module ...</module>
						</modules>
						<draftCompile>true</draftCompile>
					</configuration>
				</plugin>
			</plugins>
		</build>
	</profile>
</profiles>
```

Running in SuperDevMode :

* Run the code server `mvn -Pdev gwt:run-codeserver`
* Go to http://localhost:9876 and save the generated bookmarklets
* In Parallel, run Jetty (with a draft compile) : `mvn -Pdev jetty:run`
* Go to http://localhost:8080/ (the link is provided on the maven console) and use the SuperDev bookmaklets to recompile the modules

## Gradle usage

Add our maven repository (or install the artifacts in yours) :
```
repositories {
    maven {
        url 'http://repo.putnami.org'
    }
    /* Use the standard repositories for other dependencies */
    mavenLocal()
    mavenCentral()
}
```
Set the needed property
```
ext {
    ...
	pwtVersion = '1.0.0-RC1'
    ...
}
```

Then add the desired dependencies
```
dependencies {
    ...
    "fr.putnami.pwt:pwt:$pwtVersion"
    /* You may want to use these plugins */
    "fr.putnami.pwt:pwt-code-editor:$pwtVersion"
    "fr.putnami.pwt:pwt-google-analytics:$pwtVersion"
    ...
}
```

There is no official GWT support in Gradle. However, there is a very nice plugin hosted on Github : [Steffen Schäffer's gwt-gradle plugin](https://github.com/steffenschaefer/gwt-gradle-plugin).
Unfortunately, today there is a little typo in the source code making impossible to define different modules between production tasks (like compile) and dev tasks (like GWT's SuperDevMode).

To work, the GWT's SuperDevMode needs some specific configuration in the module gwt.xml file (like a specific linker, etc...). So it is necessary to have the ability to define different modules between dev and production.

Therefore we've fork this plugin to bugfix it (and we did fill a bug indicating it) until the next release of this plugin. We host it in our maven repo with the following version : *0.4-PWT_patched*.

To use this plugin, add the corresponding dependency in the buildscript :
```
buildscript {
    repositories {
        maven {
            url 'http://repo.putnami.org/'
        }
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath 'de.richsource.gradle.plugins:gwt-gradle-plugin:0.4-PWT_patched'
    }
}
```

### Library usage
```
apply plugin: "gwt-base"

//Include all non java (like *.gwt.xml) files from 'src/main/java' folder in resources
sourceSets.main.resources.srcDir 'src/main/java'

// Add all sources in jar (needed for GWT compilation using this library)
jar {
	from sourceSets.main.allJava
}

gwt {
	gwtVersion="2.6.1"
	eclipse{
        // Set True if you use the Google Eclipse plugin
		addGwtContainer=false
	}
}
```


### Web application usage
```
apply plugin: "gwt"

gwt {
	gwtVersion="2.6.1"

    eclipse{
        // Set True if you use the Google Eclipse plugin
        addGwtContainer=false
    }

    // Allows to use the standard Dev mode (no more supported on Chrome for Linux or on Firefox)
	gwtDev {
		jvmArgs("-Xdebug",  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000")
	}

    // Modules used in production
	modules = ['...', '...']
    // Modules used in dev mode
	devModules = ['...', '...']

	superDev {
		noPrecompile=true
	}
}
```
#### Running in standard DevMode
*Note : the standard DevMode is no more supported on Chrome for Linux or on Firefox*

Use `gradle gwtDev`
#### Runing in SuperDevMode

##### Using Gradle Jetty plugin
Add the following configuration to Gradle build script
```
apply plugin: 'jetty'

task jettyDraftWar(type: JettyRunWar) {
    dependsOn draftWar
    dependsOn.remove('war')
    webApp=draftWar.archivePath
}
```
Running in SuperDevMode :

* Run the code server `gradle gwtSuperDev`
* Go to http://localhost:9876 and save the generated bookmarklets
* In Parallel, run Jetty (with a draft compile) : `gradle jettyDraftWar`
* Go to http://localhost:8080/{projectName} (the link is provided on the gradle console) and use the SuperDev bookmaklets to recompile the modules

##### Using Cargo plugin
If you prefer to use Cargo plugin to deploy your application in SuperDevMode, add the following configuration to Gradle build script
```
buildscript {
    repositories {
        ...
        jcenter()
    }
    dependencies {
        classpath 'org.gradle.api.plugins:gradle-cargo-plugin:1.5'
    }
}

apply plugin: 'cargo'

ext {
    // Define the cargo config home dir (else in temp folder)
	cargoConfigHomeDir = file("$buildDir/cargo/conf")
}
cargo {
    // If you want to use Jetty
	containerId = "jetty9x"
	port = 8080

	deployable {
		file = tasks.draftWar.archivePath
		context = "/"
	}

	local {
		installer {
            // Link to download Jetty distribution
			installUrl = 'http://repo1.maven.org/maven2/org/eclipse/jetty/jetty-distribution/9.1.5.v20140505/jetty-distribution-9.1.5.v20140505.tar.gz'

			downloadDir = file("$projectDir/cargo/download")
			extractDir = file("$projectDir/cargo/extract")
		}
		//If you want to provide your own distribution
		//homeDir = file("$projectDir/cargo/extract/jetty-distribution-9.1.5.v20140505/jetty-distribution-9.1.5.v20140505")
        // Define the cargo config home dir (else in temp folder)
		configHomeDir = file("$cargoConfigHomeDir")
        // Cargo output log file
		outputFile = file("$buildDir/cargo/cargoOutput.log")
	}
}

task cargoCreateConfigHomeDir << {
	file("$cargoConfigHomeDir").mkdirs()
}

// Auto run draftWar when cargo is launching
afterEvaluate {
	tasks.cargoStartLocal.dependsOn(tasks.draftWar, tasks.cargoCreateConfigHomeDir)
	tasks.cargoRunLocal.dependsOn(tasks.draftWar, tasks.cargoCreateConfigHomeDir)
}
```
Running in SuperDevMode :

* Run the code server `gradle gwtSuperDev`
* Go to http://localhost:9876 and save the generated bookmarklets
* In Parallel, run Cargo (with a draft compile) : `gradle cargoRunLocal`
* Go to http://localhost:8080/{projectName} (the link is provided on the gradle console) and use the SuperDev bookmaklets to recompile the modules

# Build #
We use Gradle to build our framework.

The project is organized that way :

```
.
+-- core    => contains the core framework project
+-- doc     => contains the documentation webapp project
+-- samples => contains the samples projects
+-- plugins => contains the provided plugins projects
```
The samples projects are not children of the root project. They ave to be build separately.

On the repository root we provide some useful tasks :

* **publishAll** => publish all sub-projects to the defined maven repo
* **publishAllToMavenLocal** => publish all sub-projects to the local maven repo
* **publishAllLibs** => publish all non webApp sub-projects (today all projects but doc) to the defined maven repo
* **publishAllLibsToMavenLocal** => publish all non webApp sub-projects (today all projects but doc) to the local maven repo
* **licenseAll** => Check the license header in the files of all sub-projects
* **licenseFormatAll** => Check and add if necessary the license header in the files of all sub-projects

All the publishing tasks depends on the *licenseFormat* task : publishing a project always check and update update if needed the license headers.


If you want to do build the framework yourself, it's very simple :

* Use `gradle jar` on the repository root to generate the jars, which will be in the project's build/libs folder.
* Use `gradle jar` on the project root to generate the project jar, which will be in the project's build/libs folder.
* Use `gradle publishAllLibsToMavenLocal` on the repository root to publish all projects except doc in your local maven repo.

# Contribute #
If you want to contribute to this project, you can report issues or submit your ideas for new amazing features [here](https://github.com/Putnami/putnami-pwt/issues)

We're unfortunatly not ready to accept pullrequest. All the docs and tools concerning code style, code formating, commit pattern is still missing.


---

---

We hope that this framework will help you to build great apps. Best regards.

[@Putnami team](https://github.com/putnami)
