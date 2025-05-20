# CornerLabelTextView

[![](https://img.shields.io/maven-central/v/io.github.praveensinghshekhawat/cornerlabeltextview.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.praveensinghshekhawat/cornerlabeltextview)

A customizable Android TextView to show corner labels like **SALE**, **NEW**, etc. Easily add labels
to your views without complex layouts.
A fully customizable **Corner Label TextView** for Android — Add stylish corner labels to your
TextViews with just a few XML attributes!

---

## ✨ Features

- 🔹 Add corner labels to TextViews
- 🎨 Customize label text, size, color, background
- ↔️ Choose between left or right corner
- 🪶 Lightweight, no heavy dependencies

---

## 🚀 Installation

<h2> 📦 Dependency </h2>

<b>➡️ Gradle (Groovy)</b>
```gradle
dependencies {
    implementation 'io.github.praveensinghshekhawat:cornerlabeltextview:1.0.2'
}
```

<b>➡️ Gradle (Kotlin DSL)</b>
```gradle
dependencies {
    implementation("io.github.praveensinghshekhawat:cornerlabeltextview:1.0.2")
}
```


## 🧩 Usage
```XML Example:

<io.github.praveensinghshekhawat.CornerLabelTextview
    android:id="@+id/txt1"
    android:layout_height="100dp"
    android:layout_width="100dp"
    app:cornerLabelBackgroundColor="@color/black"
    app:cornerLabelLength="30dp" 
    app:cornerLabelMode="left"
    app:cornerLabelText="CornerLabel" 
    app:cornerLabelTextColor="@color/white"
    app:cornerLabelTextSize="10sp"
    android:gravity="center"/>
```
        
## 🔧 Custom Attributes:

| Attribute                    | Description                 |
| ---------------------------- | --------------------------- |
| `cornerLabelText`            | Text inside the label       |
| `cornerLabelTextSize`        | Size of label text          |
| `cornerLabelTextColor`       | Label text color            |
| `cornerLabelBackgroundColor` | Label background color      |
| `cornerLabelLength`          | Length of the label (in dp) |
| `cornerLabelMode`            | `left` or `right`           |



## ⚙️ Requirements

- `minSdkVersion: 24`
- `compileSdkVersion: 34`
- `Java: 11 or above`



## ️ 💻 Developer/Author

🙋‍ Praveen Singh Shekhawat  
📧 praveensinghshekhawat8@gmail.com  
🔗 [GitHub Profile](https://github.com/praveensinghshekhawat)  

## ☕ Support Me
If you found this library helpful, consider buying me a coffee 💛  
[![](https://img.shields.io/badge/-Buy%20me%20a%20coffee-yellow?logo=buymeacoffee&logoColor=black&style=flat-square)](https://buymeacoffee.com/praveensinghshekhawat)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.