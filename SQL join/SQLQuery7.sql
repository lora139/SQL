USE AdventureWorks2019
SELECT a.AuthorID, a.AuthorName,
e.EpisodeID, e.SeriesNumber, e.EpisodeNumber, e.EpisodeType, e.Title, e.EpisodeDate, e.DoctorID, e.Notes
FROM tblAuthor AS a, tblEpisode AS e
WHERE a.AuthorID=e.AuthorID;