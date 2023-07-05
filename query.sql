
-- Revenus Locatif

SELECT 
revenulocatif.id_revenuLocatif,
revenulocatif.id_immeuble,
revenulocatif.loyerExonere, 
revenulocatif.loyerImposable,
revenulocatif.chargeIncombat,
(revenulocatif.loyerExonere + revenulocatif.loyerImposable) AS "revenuBrut" ,
((revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) AS "deductionPourDepenses" ,
revenulocatif.interetEmprunt,
(((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) AS " Revenus nets imposables "
FROM revenulocatif;

String a ="SELECT revenulocatif.id_revenuLocatif, revenulocatif.id_immeuble, revenulocatif.loyerExonere, revenulocatif.loyerImposable, revenulocatif.chargeIncombat, (revenulocatif.loyerExonere + revenulocatif.loyerImposable) AS "revenuBrut" , ((revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) AS "deductionPourDepenses" , revenulocatif.interetEmprunt, (((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) AS " Revenus nets imposables " FROM revenulocatif";


-- Revenus de sous Location
SELECT
(((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) AS " Revenus nets imposables ",
revenusouslocation.id_revenuSousLocatif, 
revenusouslocation.id_revenuLocatif,
revenusouslocation.recetteSousLocation, 
revenusouslocation.loyerPayes,
(revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes) AS "Revenus_nets_imposables " , 
((((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) + (revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes)) AS "Total_revenus_nets_imposables" , 
revenusouslocation.abbattements,
((((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) + (revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes)) - revenusouslocation.abbattements AS "REVENU_NET_IMPOSABLE "
FROM revenusouslocation, revenulocatif
WHERE revenulocatif.id_revenuLocatif = revenusouslocation.id_revenuLocatif

String b = "SELECT (((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) AS " Revenus nets imposables ", revenusouslocation.id_revenuSousLocatif, revenusouslocation.id_revenuLocatif, revenusouslocation.recetteSousLocation, revenusouslocation.loyerPayes, (revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes) AS "Revenus_nets_imposables " , ((((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) + (revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes)) AS "Total_revenus_nets_imposables" , revenusouslocation.abbattements, ((((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) + (revenusouslocation.recetteSousLocation - revenusouslocation.[...]
"

-- Revenu locatif