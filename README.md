# Object-Oriented Programming Assignments (BBM 104)

Bu repo, Java tabanlı Nesne Yönelimli Programlama (OOP) laboratuvar projelerini içermektedir.

Tüm projeler **Java 8 (Oracle)** standardında, OOP'nin 4 temel direği (**Encapsulation, Inheritance, Polymorphism, Abstraction**), arayüzler (Interfaces), dosya girdi/çıktı işlemleri (File I/O) ve özel hata yönetimi (Custom Exception Handling) prensiplerine uygun olarak tasarlanmıştır.

---

## 📁 Proje İçerikleri

### 1. [PA1: University Library Management System](./PA1)
* **Kapsam:** Üniversite kütüphanesindeki materyallerin (kitap, dergi, DVD) kullanıcı rolleri (öğrenci, akademisyen, misafir) tarafından ödünç alma, iade etme ve ceza/gecikme süreçlerini yöneten sistem.
* **OOP & Tasarım Detayları:**
  * **Kalıtım & Kapsülleme (Inheritance & Encapsulation):** Genel kütüphane materyalleri (`Item` taban sınıfı) üzerinden türeyen `Book`, `Magazine` ve `DVD` sınıfları; kullanıcılar için `User` taban sınıfından türeyen `Student`, `AcademicMember` ve `Guest` yapıları.
  * **İş Kuralları ve Kısıtlar:**
    * Ödünç alma limitleri (Öğrenci: 5, Akademisyen: 3, Misafir: 1).
    * Gecikme süreleri (30, 15 ve 7 gün) ve gün aşımında uygulanan $2 ceza kuralı.
    * Ceza eşiği (≥ $6) aşıldığında işlem blokajı ve borç ödeme mekanizması.
    * Referans, nadir (rare) ve kısıtlı (limited) kaynaklar için kullanıcı bazlı kısıtlamalar.
* **Girdi / Çıktı:** `items.txt`, `users.txt` ve `commands.txt` dosyalarından verilerin okunup işlemlerin sıralı olarak `output.txt` dosyasına yazdırılması.

---

### 2. [PA2: Zoo Manager](./PA2)
* **Kapsam:** Hayvanat bahçesindeki farklı hayvan türlerinin beslenme/bakım gereksinimlerini ve personel ile ziyaretçilerin yetkilerini simüle eden yönetim sistemi.
* **OOP & Tasarım Detayları:**
  * **Soyutlama & Çok Biçimlilik (Abstraction & Polymorphism):** `Animal` ve `Person` soyut sınıfları/arayüzleri. Her hayvan türü (`Lion`, `Elephant`, `Penguin`, `Chimpanzee`) için yaşa bağlı olarak formüle edilen dinamik porsiyon hesaplamaları ve türe özgü kafes temizleme adımları.
  * **Yetkilendirme:** Personel (`Personnel`) hem besleme hem de kafes temizleme yetkisine sahipken; ziyaretçiler (`Visitor`) yalnızca ziyaret kaydı oluşturabilir, besleme yapamaz.
  * **Özel Hata Yönetimi (Custom Exceptions):** Yetersiz besin stoğu (et, bitki, balık), yetkisiz ziyaretçi besleme girişimleri ve sistemde bulunmayan kişi/hayvan durumları için program akışını kesmeyen özel istisna sınıfları.
* **Girdi / Çıktı:** `animals.txt`, `person.txt`, `foods.txt` ve `commands.txt` dosyalarının işlenmesi ve stok takibi.

---

### 3. [PA3: University Student Management System](./PA3)
* **Kapsam:** Öğrenciler, öğretim üyeleri, bölümler, lisans programları ve dersler arasındaki karmaşık akademik ilişkileri, notlandırmayı ve raporlamayı gerçekleştiren kapsamlı otomasyon sistemi.
* **OOP & Tasarım Detayları:**
  * **İlişki Yönetimi & Modüler Mimari:**
    * Bir bölümün (`Department`) bir bölüm başkanı (`Head`) ile eşleştirilmesi.
    * Lisans programlarının (`Program`) ilgili bölüme ve derslere bağlanması.
    * Öğretim üyelerinin ders atamaları ve öğrencilerin ders kayıtları.
  * **Hesaplama Motoru & Raporlama:**
    * 4'lük not sistemi üzerinden ders başarı ortalamaları ve harf notu dağılımları (`A1`–`F3`).
    * Kredi ağırlıklı Genel Not Ortalaması (GPA) hesabı ve detaylı transkript/öğrenci raporlarının üretilmesi.
  * **Hata Yönetimi:** Geçersiz harf notları, bulunamayan öğrenci, öğretim üyesi, ders, program veya bölüm girdilerine karşı güvenli hata yakalama mimarisi.
* **Girdi / Çıktı:** `persons.txt`, `departments.txt`, `programs.txt`, `courses.txt`, `assignments.txt`, `grades.txt` girdilerinden yapılandırılmış akademik raporların oluşturulması.

---

## 🛠 Kullanılan Teknolojiler ve Beceriler

* **Programlama Dili:** Java 8 (Oracle)
* **Tasarım İlkeleri:** Object-Oriented Programming (Inheritance, Polymorphism, Abstraction, Encapsulation)
* **Veri Yönetimi & Akış:** Java Collections Framework, File I/O (`BufferedReader`, `FileReader`, `PrintWriter`)
* **Hata Yönetimi:** Custom Exception Handling, Try-Catch Blokları
* **Dokümantasyon:** JavaDoc standartlarında kod içi dokümantasyon
