# LeagueManager

- Uygulamada local veritabanı için SQLite kullanıldı.
- Uygulama, takım verilerini https://611ea8d49771bf001785c5c2.mockapi.io/teamList/v1/teams adresinden çekmektedir.
- Uygulamada picasso ve circleview kütüphaneleri kullanıldı. Bu sayede takımlara ait fotoğrafları içeren URL ler kullanılarak takım fotoğrafları uygulamada görüntülendi.
- Uygulama MVVM mimarisine uygun olarak tasarlandı.

************************************************************

Uygulama, takım listelerinin ilgili URL'e RXJava ile istek gönderip, ardından fixture algoritmasının 
oluşturulup bu verilerin local veritabanında saklanması temeli ile oluşturuldu.


Takıma ait veriler (Team model class) :
 - id : int
 - isim : String
 - takım kodu : String
 - photoURL :  string


Uygulama çalıştırıldığı anda ilk işlem API den url e istek gönderilir ve local veritabanının boş olup olmadığı kontol edilir. 

  - Eğer boş ise, ilgili fixture algoritması çalışır ve gelen takım sayısına göre tüm eşleşmeler hazırlanıp, local veritabanına kaydedilir.
  
  - Eğer boş değil ise ikinci kontrol yapılır. API den gelen takım verileri ile local veritabanında saklanan takım verileri birbiri ile aynı mı?
    
    - Eğer aynı ise => Gelen takım verilerinde bir değişiklik olmadığı için, daha önce veritabanında saklanmış olan fixture verileri kullanılır.
    - Eğer aynı değil ise => API den gelen takım verilerinde değişiklik olmuş. Bu durumda takım sayısı vs değişebilme ihtimaline karşı veritabanındaki tüm veriler temizlenir ve gelen verilere göre fixture algoritması çalıştırılıp local veritabanına kaydedilir.
    
   
 Fixture Algoritması : 
 
 Matches sınına ait veriler (Matches model class) :
 - teamOne : Team
 - teamTwo : Team
 - Date : String
 
 
 Takımlara ait veriler, 1 den N e kadar takımların bulunduğu bir ArrayList olarak tanımlandı. 
 Bu ArrayList üzerinden ilk eleman ile kendisinden sonra gelen tüm elemanlar eşleştirildi.
 Daha sonra ikinci eleman ile kendisinden sonra gelen tüm elemanlar eşleştirildi.
 Bu işlem sondan ikinci elemana kadar tekrarlandı.
 
 Oluşan bu eşleşmeler Matches tipine ait yeni bir ArrayList e atandı ve bu ArrayList, shuffle metodu ile karıştırıldı.
 Ardından aynı elemanlar yeniden eklendi. (Fixture un ikinci yarısını oluşturmak için)
 
 Son aşama olarak tüm liste boyunca her 14 eşleşmede bir, tarih 1 hafta ötelenerek atandı. Böylece haftada 14 maç ayarlanmış oldu.
 
 **************************************************
 Classlar ve görevleri :
 
 MainActivity : Uygulama açıldığı anda takım verilerinin görüntülendiği ana ekran. Gelen veriler, API den gelen sonuçların listesidir.

 FixtureActivity : Eşleşmelerin hafta hafta gösterildiği ekrandır. Uygulama çalıştırıldığında aynı takım verileri gelmişse, buradaki liste local veritabanından alınmıştır.
 
 
 MatchesAdapter : Eşleşme verilerinin recyclerview ile görüntülenmesini sağlayan adapter sınıfıdır.
 
 TeamListAdapter : Takım verilerinin MainActivity de liste halinde görüntülenmesini sağlayan adapter sınıfıdır.
 
 ViewPagerAdapter : FixtureActivity sayfası üzerinde sağa ve sola kaydırılabilir ekran özelliği sunan adapter sınıfıdır.
 
 
 Calculation : İçersindeki Calculate statik fonksiyonu sayesinde, fixture algoritmasının oluşturulmasını sağlayan hesaplama sınıfıdır. Veriler ilk defa geldiğinde, bu sınıfın 
 fonksiyonu çağırılır ve ilgili ArrayList<Matches> verileri oluşturulup, local veritabanına kaydedilir.
 
 
 DB : Local veritabanı üzerinde Takımların ve Takım eşleştirilmelerine ait (Matches) tablolarının ve verilerinin kaydedildiği SQLite tan extend alan sınıftır.
 
 
 Model classlar: Team ve Matches verilerinin oluşturulduğu sınıflardır.
 
 
 APIService (Interface)  ve RetroInstance : RXJava ile oluşturulan retrofit objesinin oluşturulduğu sınıftır. Base url e istek atma fonksiyonu kendi içinde hazırda bekletilir.
 
 
 MyViewModel : URL e gönderilen istekler ilk aşamada bu class üzerinden gönderilir. İçeriğindeki makeGetRequest() fonksiyonu, verileri teamList değişkenine aktarır.
 
 
 
 
 
 
 
 
