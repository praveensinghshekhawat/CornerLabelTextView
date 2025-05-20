import cn.lalaki.pub.BaseCentralPortalPlusExtension
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
    id("signing")
    id("cn.lalaki.central") version "1.2.8"
}

android {
    namespace = "io.github.praveensinghshekhawat.cornerlabeltextview"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    compileOnly(libs.appcompat)
    compileOnly(libs.material)
}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

val localMavenRepo =
    uri(localProperties["localMavenRepoPath"] as String)

centralPortalPlus {
    url = localMavenRepo
    username = localProperties["sonatypeUsername"] as String
    password = localProperties["sonatypePassword"] as String
    tokenXml = uri(localProperties["tokenXmlPath"] as String)
    publishingType =
        BaseCentralPortalPlusExtension.PublishingType.USER_MANAGED // or PublishingType.AUTOMATIC
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }

            // groupId, artifactId, version
            groupId = "io.github.praveensinghshekhawat"
            artifactId = "cornerlabeltextview"
            version = "1.0.2"

            pom {
                name.set("CornerLabelTextView")
                description.set("A customizable corner label TextView for Android.")
                url.set("https://github.com/praveensinghshekhawat/cornerlabeltextview")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("praveensinghshekhawat")
                        name.set("Praveen Singh Shekhawat")
                        email.set("praveensinghshekhawat8@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/praveensinghshekhawat/cornerlabeltextview.git")
                    developerConnection.set("scm:git:ssh://github.com/praveensinghshekhawat/cornerlabeltextview.git")
                    url.set("https://github.com/praveensinghshekhawat/cornerlabeltextview")
                }
            }

        }
    }

    repositories {
        maven {
            url = localMavenRepo
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["release"])
}
