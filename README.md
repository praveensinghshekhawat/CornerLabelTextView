# CornerLabelTextView

[![](https://img.shields.io/maven-central/v/io.github.praveensinghshekhawat/cornerlabeltextview.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.praveensinghshekhawat/cornerlabeltextview)

A customizable Android TextView to show corner labels like **SALE**, **NEW**, etc. Easily add labels
to your views without complex layouts.
A fully customizable **Corner Label TextView** for Android — Add stylish corner labels to your
TextViews with just a few XML attributes!

---


## Preview

<table>
  <tr>
    <td><img src="https://github.com/praveensinghshekhawat/CornerLabelTextView/blob/main/Screenshots/image_1.jpg" width="300"/></td>
    <td><img src="https://github.com/praveensinghshekhawat/CornerLabelTextView/blob/main/Screenshots/image_2.jpg" width="300"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/praveensinghshekhawat/CornerLabelTextView/blob/main/Screenshots/image_3.jpg" width="300"/></td>
    <td><img src="https://github.com/praveensinghshekhawat/CornerLabelTextView/blob/main/Screenshots/image_4.jpg" width="300"/></td>
  </tr>
</table>

## Screenshots

<img src="https://github.com/praveensinghshekhawat/CornerLabelTextView/blob/main/Screenshots/screenshot_1.jpg" width="300"/>


## ✨ Features

- 🔹 Add corner labels to TextViews
- 🎨 Customize label text, size, color, background
- ↔️ Choose between left or right corner
- 🪶 Lightweight, no heavy dependencies

---

## 🚀 Installation

<h3> 📦 Dependency </h3>

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

<b>XML Example:</b>
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
    app:cornerLabelTextSize="10sp" />
```

<b>Java Example:</b>
```Java Example:

CornerLabelTextview stv = (CornerLabelTextview) findViewById(R.id.txt1);
        stv.setText("CornerLabel")
                .setTextColor(Color.WHITE)
                .setCornerLabelBackgroundColor(Color.BLACK)
                .setTextSize(18)
                .setCornerLabelLength(50)
                .setMode(CornerLabelTextview.LabelMode.MODE_LEFT);
```
        
## 🔧 Custom Attributes:

| Attribute                    | Description                 |
| ---------------------------- | --------------------------- |
| `cornerLabelText`            | Text inside the label       |
| `cornerLabelTextSize`        | Size of label text          |
| `cornerLabelTextColor`       | Label text color            |
| `cornerLabelBackgroundColor` | Label background color      |
| `cornerLabelLength`          | Length of the label (in dp) |
| `cornerLabelMode`            | `left``right``left_bottom`<br/>`right_bottom``left_triangle``right_triangle`<br/>`left_bottom_triangle``right_bottom_triangle`          |



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
[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://buymeacoffee.com/praveensinghshekhawat)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.