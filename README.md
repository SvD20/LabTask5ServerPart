# LabTask5ServerPart
Серверная часть для лабораторной работы 5.

Сервер взаимодействует с клиентом по стеку протоколов tcp/ip. Принимает sql запрос от клиента и перенаправляет его в СУБД MySQL.
Сервер, как и в предыдущих работах, многопоточный. Прописана только логика для реализации SELECT запрос, потому что я перешел на более удобный 
способ взаимодействия приложения с СУБД - фреймворк Hibernate.

