
--administrateur & role

 SELECT 
 A.*, R.id As role_ID, 
 R.nomRole AS role_name 
 FROM administrateur A, role R 
 WHERE R.id = A.id_Role 
 AND A.id = 1 


--contribuable & representant

SELECT 
C.*, 
R.id As representant_ID, 
R.nomRepresentant As nom_representant, 
R.prenomRepresentant AS prenom_representant, 
R.emailRepresentant AS email_representant, 
R.telephoneRepresentant AS tel_representant, 
R.bpRepresentant AS BP_representant 
FROM contribuable C, representant R
WHERE C.id_representant = R.id 
AND C.id = 2; 


--for 4 tables

SELECT  contribuable.*, immeuble.id as immeuble_id, colline.nomColline,  commune.nomCommune, province.nomProvince
FROM contribuable
INNER JOIN immeuble ON immeuble.id_contibuable = contribuable.id
LEFT JOIN colline ON immeuble.id_colline = colline.id
RIGHT JOIN commune ON colline.id_commune = commune.id;


-- query for immeuble for 5 tables

SELECT  contribuable.*, immeuble.id as immeuble_id, immeuble.nomAvenue as Rue , colline.nomColline as Colline,  commune.nomCommune as Commune, province.nomProvince as Province
FROM contribuable
JOIN immeuble ON immeuble.id_contibuable = contribuable.id
JOIN colline ON immeuble.id_colline = colline.id
JOIN commune ON colline.id_commune = commune.id
JOIN province ON commune.id_province = province.id;



SELECT  
contribuable.id As Contribuable_ID,
contribuable.nom As nom_contribuable,
contribuable.prenom AS prenom_contribuable,
contribuable.email AS email_contribuable,
contribuable.telephone AS tel_contribuable,
contribuable.BP AS BP_contribuable,
representant.id As representant_ID,
representant.nomRepresentant As nom_representant,
representant.prenomRepresentant AS prenom_representant,
representant.emailRepresentant AS email_representant,
representant.telephoneRepresentant AS tel_representant,
representant.bpRepresentant AS BP_representant,
immeuble.id as immeuble_id, 
immeuble.nomAvenue as Rue , 
colline.nomColline as Colline, 
commune.nomCommune as Commune, 
province.nomProvince as Province
FROM representant
JOIN contribuable ON contribuable.id_representant = representant.id
JOIN immeuble ON immeuble.id_contibuable = contribuable.id
JOIN colline ON immeuble.id_colline = colline.id
JOIN commune ON colline.id_commune = commune.id
JOIN province ON commune.id_province = province.id;


String query = "SELECT contribuable.id As Contribuable_ID, contribuable.nom As nom_contribuable, contribuable.prenom AS prenom_contribuable, contribuable.email AS email_contribuable, contribuable.telephone AS tel_contribuable, contribuable.BP AS BP_contribuable, representant.id As representant_ID, representant.nomRepresentant As nom_representant, representant.prenomRepresentant AS prenom_representant, representant.emailRepresentant AS email_representant, representant.telephoneRepresentant AS tel_representant, representant.bpRepresentant AS BP_representant, immeuble.id as immeuble_id, immeuble.nomAvenue as Rue , colline.nomColline as Colline,  commune.nomCommune as Commune,  province.nomProvince as Province FROM representant JOIN contribuable ON contribuable.id_representant = representant.id JOIN immeuble ON immeuble.id_contibuable = contribuable.id JOIN colline ON immeuble.id_colline = colline.id JOIN commune ON colline.id_commune = commune.id JOIN province ON commune.id_province = province.id";


