<div dir="rtl">

# Create Example Addon: Computer Tech 👨‍💻

**مود افزودنی برای Create — تکنولوژی کامپیوتر و رسانه در ماینکرفت**

| | |
|---|---|
| **نسخه مود** | 0.1.0 |
| **ماینکرفت** | 1.21.1 |
| **NeoForge** | 21.1.267 |
| **Create** | 6.0.10 |
| **Java** | 21 |
| **مجوز** | MIT |

این مود یک زنجیره ساخت کامل از تکنولوژی کامپیوتری به بازی اضافه می‌کند: از **شن و زغال** شروع می‌کنید و در نهایت به **کامپیوتر اسمبل‌شده، گوشی هوشمند و تبلت** می‌رسید. در قلب این زنجیره یک ماشین جدیدِ سینماتیک به نام **دستگاه ساخت تراشه (Chip Fabricator)** قرار دارد که مثل آسیاب (Millstone) با نیروی چرخشی کار می‌کند.

---

## 📋 پیش‌نیازها

| نرم‌افزار | نسخه | توضیح |
|---|---|---|
| JDK | 21 | حتماً نسخه 21، نه 17 یا 22 |
| IntelliJ IDEA | آخرین نسخه | نسخه Community رایگان کافی است |
| Git | هر نسخه | برای کلون کردن پروژه |
| RAM | حداقل 8 گیگ | 16 گیگ بهتر است |

> **نکته:** نیازی به نصب جداگانه Gradle ندارید — پروژه از Gradle Wrapper (فایل `gradlew`) استفاده می‌کند که خودش همه‌چیز را دانلود می‌کند.

---

## 🚀 گام ۱: نصب JDK 21

1. به سایت [Adoptium](https://adoptium.net/temurin/releases/?version=21) بروید.
2. **Operating System** را روی سیستم‌عامل خود و **Architecture** را روی معماری CPU خود (معمولاً x64) بگذارید.
3. فایل نصب را دانلود کنید:
   - **ویندوز:** فایل `.msi` را اجرا کنید. در صفحه تنظیمات، حتماً گزینه‌های **"Set JAVA_HOME variable"** و **"Add to PATH"** را فعال کنید.
   - **لینوکس:** فایل `.tar.gz` را باز کنید یا از دستور زیر استفاده کنید:
     ```bash
     sudo apt install temurin-21-jdk
     ```
   - **مک:** فایل `.pkg` را اجرا کنید.
4. صحت نصب را تست کنید — یک ترمینال (CMD در ویندوز) باز کنید:
   ```bash
   java -version
   ```
   باید خروجی مشابه این ببینید:
   ```
   openjdk version "21.0.x" ...
   ```

---

## 📥 گام ۲: دریافت پروژه

**روش اول — کلون با Git (پیشنهادی):**

```bash
git clone <آدرس-ریپوی-شما>
cd createexample
```

**روش دوم — دانلود ZIP:**

بالای همین صفحه روی دکمه سبز **Code** کلیک کنید و **Download ZIP** را بزنید، سپس آن را باز (extract) کنید.

---

## 💡 گام ۳: باز کردن پروژه در IntelliJ

1. IntelliJ را باز کنید: **File → Open** (اولین بار: **Open**)
2. پوشه `createexample` را انتخاب کنید — دقت کنید **خودِ پوشه پروژه** را انتخاب کنید، نه پوشه بالادست را.
3. اگر پیام **"Trust Project?"** آمد، روی **Trust** کلیک کنید.
4. IntelliJ به‌صورت خودکار Gradle Sync را شروع می‌کند (پایین صفحه نوار پیشرفت می‌بینید).
   - ⏳ **صبور باشید:** اولین بار بین ۵ تا ۲۰ دقیقه طول می‌کشد چون Gradle، NeoForge، مپ‌ها و خودِ Create را دانلود می‌کند.
   - وقتی پایین صفحه بنویسب **"BUILD SUCCESSFUL"** یا نوار پیشرفت تمام شود، آماده‌اید.
5. اگر پیغام **"Project JDK is missing"** یا **"No SDK"** دیدید:
   - **File → Project Structure → Project**
   - **SDK** را روی JDK 21 که در گام ۱ نصب کردید بگذارید.
   - **Language level** را روی `SDK default (21)` بگذارید.

---

## 🎮 گام ۴: اجرای کلاینت توسعه (runClient)

**روش اول — از پنل Gradle:**

1. لبه سمت راست IntelliJ روی آیکون **Gradle** (فیل) کلیک کنید.
2. مسیر زیر را باز کنید:
   ```
   createexample → Tasks → neoforge → runClient
   ```
3. روی `runClient` **دابل‌کلیک** کنید.

**روش دوم — از ترمینال IntelliJ (پایین صفحه):**

```bash
./gradlew runClient
```
در ویندوز:
```bash
gradlew.bat runClient
```

⏳ اولین اجرا چند دقیقه طول می‌کشد. بعد از آن ماینکرفت با مود شما لود می‌شود:

- تب خلاقیت جدید: **Create Example Addon**
- برای تست ماشین: دستگاه ساخت تراشه را بگذارید، یک **شفت (Shaft)** زیر آن وصل کنید و با آسیاب باد/آب یا چرخ دستی روشنش کنید.

---

## 📦 گام ۵: ساخت فایل jar نهایی

```bash
./gradlew build
```

فایل خروجی اینجا ساخته می‌شود:

```
build/libs/createexample-0.1.0.jar
```

> اگر خواستید سریع‌تر و بدون اجرای تست build بگیرید: `./gradlew jar`

---

## 🖥️ گام ۶: نصب در بازی اصلی (نه محیط توسعه)

برای بازی کردن با مود در لانچر اصلی:

1. **NeoForge 21.1.267** را برای ماینکرفت 1.21.1 از [سایت NeoForge](https://neoforged.net/) نصب کنید.
2. **Create 6.0.10** را از [Modrinth](https://modrinth.com/mod/create) یا [CurseForge](https://www.curseforge.com/minecraft/mc-mods/create) دانلود کنید (نسخه 1.21.1 + NeoForge).
3. **Ponder** را هم دانلود کنید (Create به آن نیاز دارد — در صفحه Modrinth Create لینک شده).
4. هر سه فایل jar را در پوشه `mods` بگذارید:
   - ویندوز: `%APPDATA%\.minecraft\mods`
   - لینوکس: `~/.minecraft/mods`
   - مک: `~/Library/Application Support/minecraft/mods`
5. لانچر را با پروفایل NeoForge اجرا کنید.

---

## 🧰 محتوای مود (۳۱ مورد)

### مواد اولیه (۴)
| آیتم | نحوه ساخت |
|---|---|
| شمش سیلیکون (Silicon Ingot) | میکسر: ۲ شن + ۱ زغال |
| ویفر سیلیکون (Silicon Wafer) | پرس: شمش سیلیکون |
| میکروچیپ (Microchip) | **دستگاه ساخت تراشه**: ویفر سیلیکون |
| برد مدار (PCB) | میز ساخت |

### قطعات کامپیوتر (۷)
`CPU` · `رم (RAM Stick)` · `مادربرد (Motherboard)` · `کارت گرافیک (GPU)` · `کارت صدا` · `کارت شبکه` · `پاور (PSU)`
— همه با میز ساخت از میکروچیپ + PCB؛ مادربرد، CPU و کارت گرافیک با **ساخت مکانیکی (Mechanical Crafting)** در دستگاه مونتاژ.

### دستگاه‌های ذخیره‌سازی (۶)
`هارد دیسک (HDD)` · `SSD` · `M.2 SSD` · `فلاپی‌دیسک` · `فلش USB` · `هارد اکسترنال`

### قطعات گوشی (۳)
`موتور ویبره` · `نمایشگر LCD` · `قاب گوشی`

### وسایل جانبی (۵)
`کیبورد` · `ماوس` · `هدفون` · `میکروفن` · `وب‌کم`

### دستگاه‌های نهایی (۲)
| دستگاه | نحوه ساخت |
|---|---|
| 📱 **گوشی هوشمند** | ساخت مکانیکی: قاب + LCD + موتور ویبره + میکروچیپ + PCB + رم |
| 📲 **تبلت** | ساخت مکانیکی: قطعات مشابه با اندازه بزرگ‌تر |

### بلوک‌ها (۴)
| بلوک | توضیح |
|---|---|
| ⚙️ **دستگاه ساخت تراشه** | ماشین سینماتیک جدید — توضیح پایین ⬇️ |
| 🖥️ **کیس کامپیوتر** | ساخت مکانیکی ۵×۵ از مادربرد + قطعات |
| 🖥️ **مانیتور** | تزئینی — برای میز کار شما |
| 🔊 **اسپیکر** | تزئینی |

### ♻️ بازیافت (Crushing)
هارد دیسک و گوشی هوشمند را در سنگ‌شکن بگذارید تا موادشان را پس بگیرید!

---

## ⚙️ دستگاه ساخت تراشه (Chip Fabricator)

ماشین جدید سینماتیک که مثل **آسیاب Create** کار می‌کند:

- **منبع قدرت:** شفت (Shaft) — از **پایین** دستگاه وصل کنید
- **ورودی:** آیتم را **از بالا** روی آن بیندازید (قیف یا بازوی مکانیکی) یا با راست‌کلیک داخلش بگذارید
- **خروجی:** راست‌کلیک کنید تا بردارید
- **سرعت:** هرچقدر دور موتور بیشتر، ساخت سریع‌تر (از دور ۱۶ به بالا اثر می‌گذارد)
- **سختی چرخش (Stress):** ۱۲ واحد
- **ویفر سیلیکون → میکروچیپ** در ۷.۵ ثانیه (با سرعت ۱)

---

## 🗂️ ساختار پروژه

```
createexample/
├── build.gradle                  ← تنظیمات ساخت + مخزن Maven مربوط به Create
├── gradle.properties             ← شماره نسخه‌ها (NeoForge, Create, ...)
├── settings.gradle
├── gradlew / gradlew.bat         ← Gradle Wrapper (بدون نیاز به نصب Gradle)
└── src/main/
    ├── java/com/example/createexample/
    │   ├── CreateExampleAddon.java        ← نقطه ورود مود (ثبت‌ها)
    │   ├── registry/                      ← ثبت بلوک‌ها، آیتم‌ها، ...
    │   │   ├── ModBlocks.java
    │   │   ├── ModItems.java
    │   │   ├── ModBlockEntities.java
    │   │   ├── ModCreativeTabs.java
    │   │   └── ModRecipes.java
    │   ├── content/                       ← منطق ماشین
    │   │   ├── ChipFabricatorBlock.java       ← بلوک (شکل، شفت از پایین)
    │   │   ├── ChipFabricatorBlockEntity.java ← تیک، اسلات‌ها، NBT
    │   │   └── ChipFabricatingRecipe.java     ← نوع دستور ساخت اختصاصی
    │   └── client/                        ← کد سمت کلاینت
    │       ├── ChipFabricatorRenderer.java    ← رندر چرخ‌دنده چرخان
    │       ├── ModPartialModels.java          ← مدل داخلی چرخ‌دنده
    │       └── CreateExampleAddonClient.java  ← ثبت رندرر
    └── resources/
        ├── META-INF/neoforge.mods.toml    ← شناسنامه مود + وابستگی‌ها
        ├── assets/createexample/
        │   ├── lang/en_us.json            ← متن انگلیسی
        │   ├── lang/fa_ir.json            ← متن فارسی!
        │   ├── blockstates/  ├── models/  ├── textures/
        └── data/createexample/recipe/     ← همه دستورهای ساخت
            ├── mixing/  pressing/  crushing/
            ├── chip_fabricating/          ← دستورهای ماشین جدید
            ├── crafting/                  ← دستورهای میز ساخت
            └── mechanical_crafting/       ← دستورهای ساخت مکانیکی
```

---

## ✏️ شخصی‌سازی

### افزودن آیتم جدید

1. در `registry/ModItems.java` یک `Registrate` یا ثبت ساده اضافه کنید (الگوی آیتم‌های موجود را کپی کنید).
2. مدل را در `models/item/<نام>.json` بسازید (از یک فایل موجود کپی بگیرید و مسیر texture را عوض کنید).
3. بِلوک‌بندِ آیتم را در `assets/textures/item/<نام>.png` بگذارید.
4. نام را در هر دو فایل `lang/en_us.json` و `lang/fa_ir.json` اضافه کنید.

### افزودن دستور ساخت جدید برای دستگاه ساخت تراشه

فایل JSON جدید در `data/createexample/recipe/chip_fabricating/` بسازید:

```json
{
  "type": "createexample:chip_fabricating",
  "ingredients": [
    { "item": "createexample:silicon_wafer" }
  ],
  "results": [
    { "id": "createexample:microchip" }
  ],
  "processingTime": 150
}
```

> `processingTime` بر پایه تیک است — ۲۰ تیک = ۱ ثانیه.

### تغییر نسخه‌ها

همه شماره نسخه‌ها در `gradle.properties` هستند — فقط همان یک فایل را عوض کنید.

---

## 🔧 رفع اشکال

| مشکل | راه‌حل |
|---|---|
| `Unsupported class file major version` | JDK شما 21 نیست — گام ۱ را دوباره چک کنید (`java -version`) |
| Gradle Sync گیر کرده | کش را پاک کنید: `./gradlew --stop` سپس پوشه `.gradle` را حذف و IntelliJ را ری‌استارت کنید |
| `Could not resolve com.simibubi.create` | اینترنت/فیلترشکن — مخزن `maven.createmod.net` باید در دسترس باشد |
| مود در بازی لود نمی‌شود | مطمئن شوید Create 6.0.10 و Ponder در پوشه `mods` هستند و نسخه NeoForge شما 21.1.x است |
| خطای `Duplicate mods found` | فایل jar دوبار در پوشه `mods` نگذارید |
| بازی در runClient کرش کرد | لاگ را در `run/logs/latest.log` ببینید |

---

## 🤝 نحوه توسعه بیشتر

- **کد:** `src/main/java` — کاملاً طبق الگوی سورس Create (سینتکس مشابه)
- **دو زبان:** فارسی و انگلیسی — فایل `fa_ir.json` را همیشه همزمان با `en_us.json` به‌روز کنید
- **متن‌ها:** همه کلیدها با پیشوند `item.createexample` یا `block.createexample` هستند

---

## 📄 مجوز

MIT — آزادانه استفاده، تغییر و انتشار دهید.

ساخته‌شده به‌عنوان الگوی آموزشی برای ساخت افزودنی‌های Create.

</div>
