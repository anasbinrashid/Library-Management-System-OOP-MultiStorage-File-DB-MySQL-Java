create schema assignment_1;
use assignment_1;

-- Book, User and Transaction tables created

-- Book (bookid (PK), title, author, isbn, publicationyear, genre, loanstatus, baseloanfee)
-- User (userid (PK), name, email, phonenumber, address, totalloanfee, loanextension, totalloanedbooks)
-- Transaction (transactionid (PK), bookid (FK), userid (FK), transactiontype, durationindays)

select * from book;
select * from user; 
select * from transaction;

insert into user 
values (1, "Anas", "anas@nu.edu.pk", "03000000000", "Islamabad", 0, 0, 0);
insert into user 
values (2, "Babar", "babar@nu.edu.pk", "03111111111", "Lahore", 0, 0, 0);
insert into user 
values (3, "Shadab", "shadab@nu.edu.pk", "03222222222", "Rawalpindi", 0, 0, 0);
insert into user 
values (4, "Majid", "majid@nu.edu.pk", "03333333333", "Karachi", 0, 0, 0);
insert into user 
values (5, "Nawaz", "nawaz@nu.edu.pk", "03344444444", "Lahore", 0, 0, 0);

insert into book 
values (1, "Great Expectations", "Charles Dickens", "ISBN1234", 1970, "Novel", 0, 150);
insert into book 
values (2, "Physics", "Isaac Newton", "ISBN2345", 1880, "Education", 0, 300);
insert into book 
values (3, "Glossary", "William Shakespeare", "ISBN6789", 1600, "Reference", 0, 250);
insert into book 
values (4, "Numerical Computing", "D. Mitsotakis", "ISBN7788", 2020, "Mathematics", 0, 300);
insert into book 
values (5, "Blinding Lights", "The Weeknd", "ISBN6666", 2021, "Music", 0, 500);
