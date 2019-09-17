import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.BaseExtension
import java.util.Collections

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(28)

    //此配置虽然可以不配置，这里需要配置的原因是tool.sh里需要读取，
    //后面会想办法解决
    buildToolsVersion("28.0.3")

    defaultConfig {
        minSdkVersion(18)
        targetSdkVersion(22)
        applicationId = "com.fpliu.newton.font.config.sample"
        versionCode = 1568703491
        versionName = "1.0.0"

        //只需要支持中文和英文即可，其他语言不必支持
        resConfigs("en", "zh")
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDir("src/main/libs")
            java.srcDirs("src/main/kotlin")
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("../keystore.jks")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    dataBinding {
        isEnabled = false
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.findByName("release")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-common.txt")
        }
        getByName("release") {
            signingConfig = signingConfigs.findByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-common.txt", "proguard-nolog.txt")

            //只有release包才过滤
            ndk {
                abiFilters("armeabi-v7a")
            }
        }
    }

    dexOptions {
        javaMaxHeapSize = "4g"
    }

    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
        disable("ContentDescription", "HardcodedText")
    }

    compileOptions {
        //使用JAVA8语法解析
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        //设置不要放入apk的文件
        exclude("publicsuffixes.gz")
        exclude("build-data.properties")
        exclude("jsr305_annotations/Jsr305_annotations.gwt.xml")
        exclude("okhttp3/internal/publicsuffix/publicsuffixes.gz")
        exclude("kotlin/annotation/annotation.kotlin_builtins")
        exclude("kotlin/collections/collections.kotlin_builtins")
        exclude("kotlin/internal/internal.kotlin_builtins")
        exclude("kotlin/ranges/ranges.kotlin_builtins")
        exclude("kotlin/reflect/reflect.kotlin_builtins")
        exclude("kotlin/kotlin.kotlin_builtins")
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/kotlin-stdlib-common.kotlin_module")
        exclude("META-INF/kotlin-stdlib.kotlin_module")
        exclude("META-INF/kotlin-runtime.kotlin_module")
        exclude("META-INF/android.arch.core_runtime.version")
        exclude("META-INF/android.arch.lifecycle_livedata-core.version")
        exclude("META-INF/android.arch.lifecycle_runtime.version")
        exclude("META-INF/android.arch.lifecycle_viewmodel.version")
        exclude("META-INF/com.android.support_animated-vector-drawable.version")
        exclude("META-INF/com.android.support_appcompat-v7.version")
        exclude("META-INF/com.android.support_design.version")
        exclude("META-INF/com.android.support_gridlayout-v7.version")
        exclude("META-INF/com.android.support_recyclerview-v7.version")
        exclude("META-INF/com.android.support_support-compat.version")
        exclude("META-INF/com.android.support_support-core-ui.version")
        exclude("META-INF/com.android.support_support-core-utils.version")
        exclude("META-INF/com.android.support_support-fragment.version")
        exclude("META-INF/com.android.support_support-media-compat.version")
        exclude("META-INF/com.android.support_support-v4.version")
        exclude("META-INF/com.android.support_support-vector-drawable.version")
        exclude("META-INF/com.android.support_transition.version")
        exclude("META-INF/android.arch.lifecycle_livedata.version")
        exclude("META-INF/android.support.design_material.version")
        exclude("META-INF/androidx.appcompat_appcompat.version")
        exclude("META-INF/androidx.asynclayoutinflater_asynclayoutinflater.version")
        exclude("META-INF/androidx.cardview_cardview.version")
        exclude("META-INF/androidx.core_core.version")
        exclude("META-INF/androidx.coordinatorlayout_coordinatorlayout.version")
        exclude("META-INF/androidx.androidx.core_core.version")
        exclude("META-INF/androidx.cursoradapter_cursoradapter.version")
        exclude("META-INF/androidx.customview_customview.version")
        exclude("META-INF/androidx.documentfile_documentfile.version")
        exclude("META-INF/androidx.drawerlayout_drawerlayout.version")
        exclude("META-INF/androidx.fragment_fragment.version")
        exclude("META-INF/androidx.gridlayout_gridlayout.version")
        exclude("META-INF/androidx.interpolator_interpolator.version")
        exclude("META-INF/androidx.legacy_legacy-support-core-ui.version")
        exclude("META-INF/androidx.legacy_legacy-support-core-utils.version")
        exclude("META-INF/androidx.legacy_legacy-support-v4.version")
        exclude("META-INF/androidx.loader_loader.version")
        exclude("META-INF/androidx.localbroadcastmanager_localbroadcastmanager.version")
        exclude("META-INF/androidx.media_media.version")
        exclude("META-INF/androidx.print_print.version")
        exclude("META-INF/androidx.recyclerview_recyclerview.version")
        exclude("META-INF/androidx.slidingpanelayout_slidingpanelayout.version")
        exclude("META-INF/androidx.swiperefreshlayout_swiperefreshlayout.version")
        exclude("META-INF/androidx.transition_transition.version")
        exclude("META-INF/androidx.vectordrawable_vectordrawable-animated.version")
        exclude("META-INF/androidx.vectordrawable_vectordrawable.version")
        exclude("META-INF/androidx.versionedparcelable_versionedparcelable.version")
        exclude("META-INF/androidx.viewpager_viewpager.version")
        exclude("META-INF/com.google.android.material_material.version")
        exclude("META-INF/proguard")
        exclude("META-INF/proguard/androidx-annotations.pro")
        exclude("META-INF/LICENSE")
        exclude("META-INF/CHANGES")
        exclude("META-INF/README.md")
        exclude("META-INF/CERT.RSA")
        exclude("META-INF/CERT.SF")
        exclude("META-INF/Android-RxCustomDialog_release.kotlin_module")
        exclude("META-INF/business_debug.kotlin_module")
        exclude("META-INF/business_release.kotlin_module")
    }
}

dependencies {
    api(project(":ui"))

    //内存泄漏检测工具LeakCanary
    //https://github.com/square/leakcanary
    debugApi("com.squareup.leakcanary:leakcanary-android:1.5.4")
    releaseApi("com.squareup.leakcanary:leakcanary-android-no-op:1.5.4")

    //渠道包中读取渠道号
    //https://github.com/Meituan-Dianping/walle
    api("com.meituan.android.walle:library:1.1.5")

    //https://jcenter.bintray.com/com/umeng
    //友盟所有SDK的公共部分
    api("com.umeng.sdk:common:1.5.3")
    //友盟统计
    api("com.umeng.analytics:analytics:6.1.3")

    api("com.fpliu:Android-CrashHandler:1.0.0")
    api("com.fpliu:Android-StatusBar-Util:1.0.1")
    api("com.fpliu:Android-Font-Assets-Alibaba_PuHuiTi_Light:1.0.0")
}

class MyTransform : Transform() {
    override fun getName(): String = "MyTransform"

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType>
        = Collections.singleton(QualifiedContent.DefaultContentType.CLASSES)

    override fun isIncremental(): Boolean = false

    override fun getScopes(): MutableSet<in QualifiedContent.Scope>
        = Collections.singleton(QualifiedContent.Scope.PROJECT)

    override fun transform(transformInvocation: TransformInvocation) {
        val context = transformInvocation.context
        println("path = ${context.path}")
        println("temporaryDir = ${context.temporaryDir.absolutePath}")
        println("variantName = ${context.variantName}")

        val inputs = transformInvocation.inputs
        val refInputs = transformInvocation.referencedInputs

        val outputProvider = transformInvocation.outputProvider
        val outputDir = outputProvider.getContentLocation(name, outputTypes, scopes, Format.DIRECTORY)
        println("outputDir = $outputDir")

        for (input in inputs) {
            for (directoryInput in input.directoryInputs) {
                val inputFile = directoryInput.file
                println("inputFile = ${inputFile.absolutePath}")
            }
            println("------------------------")
            for (jarInput in input.jarInputs) {
                val inputFile = jarInput.file
                println("inputFile = ${inputFile.absolutePath}")
            }
        }
    }
}

class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.getByType(BaseExtension::class.java).registerTransform(MyTransform())
    }
}

//apply<MyPlugin>()
