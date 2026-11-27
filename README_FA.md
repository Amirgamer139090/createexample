# Create: Power up! — MDK پایه

این آرشیو یک پروژه شروع واقعی و حداقلی برای مود است؛ چون گفتی مخزن فعلی خالی است، از صفر ساخته شده است.

## مشخصات
- Minecraft 1.21.1
- NeoForge 21.1.235
- Java 21
- Gradle Wrapper target: 8.14.3
- NeoGradle: 7.1.38
- Mod ID: `create_powerup`
- Package: `com.powerup`

## راه‌اندازی
1. Java 21 نصب کن.
2. ZIP را استخراج و پوشه را در IntelliJ IDEA باز کن.
3. Gradle Sync را انجام بده.
4. اگر `gradle-wrapper.jar` موجود نبود، یک Gradle 8.14.3 نصب‌شده را در پوشه پروژه اجرا کن:
   `gradle wrapper --gradle-version 8.14.3`
5. سپس اجرا کن:
   - Windows: `gradlew.bat build`
   - Linux/macOS: `./gradlew build`
6. اجرای کلاینت: `gradlew.bat runClient` یا `./gradlew runClient`

## Create
وابستگی Create عمداً هنوز اضافه نشده است. نسخه و مختصات دقیق سازگار با همین نسخه Minecraft/NeoForge باید پیش از افزودن، از انتشار رسمی و فایل‌های dependency بررسی شود. این کار در مرحله Integration انجام می‌شود؛ مختصات حدسی اضافه نشده است.

## وضعیت
این MDK فقط اسکلت مود است: ورودی مود، metadata، Gradle، زبان پایه. هنوز هیچ بلوک، آیتم، ماشین، کامپیوتر یا مکانیک گیم‌پلی پیاده‌سازی نشده و Build نیز در زمان ساخت آرشیو اجرا نشده است.
