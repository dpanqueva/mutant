--insert into tbl_adn (sn_mutant, dna_mutant) values ('N','[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, CCACTG]');
--insert into tbl_adn (sn_mutant, dna_mutant) values ('N','[CTGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, CCACTG]');
--insert into tbl_adn (sn_mutant, dna_mutant) values ('N','[TTGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, CCACTG]');
--insert into tbl_adn (sn_mutant, dna_mutant) values ('N','[GTGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, CCACTG]');


-- create view vw_stats as SELECT  COUNT(*) total_quantity ,(SELECT COUNT(mh.dna_mutant) FROM tbl_adn mh WHERE sn_mutant = TRUE) mutant ,(SELECT COUNT(mh.dna_mutant) FROM tbl_adn mh WHERE sn_mutant = FALSE) human FROM tbl_adn m;