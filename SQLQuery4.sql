USE [TestDB]
GO
SELECT * FROM TestDB.dbo.Person
INSERT INTO [TestDB].[dbo].[Person] (FirstName,LastName,City,Age)

VALUES

('Bill','Smith','London',22),
('Mary','Davis','New York',53),
('Martin','Green','London',27),
('Rob','Lee','Geneva',35),
('Maria','Wilson','Paris',42)
GO


