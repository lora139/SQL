USE AdventureWorks2019
SELECT a.AuthorID, a.AuthorName,
e.EpisodeID, e.SeriesNumber, e.EpisodeNumber, e.EpisodeType, e.Title, e.EpisodeDate, e.DoctorID, e.Notes
FROM tblAuthor AS a
JOIN tblEpisode AS e
ON a.AuthorID= e.AuthorID;