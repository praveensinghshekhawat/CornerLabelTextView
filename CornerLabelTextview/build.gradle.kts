plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
    id("signing")
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
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "io.github.praveensinghshekhawat"
                artifactId = "cornerlabeltextview"
                version = "1.0.0"

                pom {
                    name.set("CornerLabelTextView")
                    description.set("A customizable Android TextView to show labels at corners like SALE, NEW, etc.")
                    url.set("https://github.com/praveensinghshekhawat/CornerLabelTextView")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
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
                        connection.set("scm:git:https://github.com/praveensinghshekhawat/CornerLabelTextView.git")
                        developerConnection.set("scm:git:ssh://github.com/praveensinghshekhawat/CornerLabelTextView.git")
                        url.set("https://github.com/praveensinghshekhawat/CornerLabelTextView")
                    }
                }
            }
        }
    }
}