drop table if exists school.groups; --my comment
drop table if exists school.courses;
drop table if exists school.students;
drop table if exists school.course_student;
--init schema

create table school.groups
(
    group_id   SERIAL NOT NULL,
    group_name character(50),
    PRIMARY KEY (group_id)
);

create table school.courses
(
    course_id          SERIAL NOT NULL,
    course_name        character(50),
    course_description character(255),
    PRIMARY KEY (course_id)
);

create table school.students
(
    student_id SERIAL NOT NULL,
    group_id   int,
    first_name character(255),
    last_name  character(255),
    PRIMARY KEY (student_id)
);

create table school.course_student
(
    course_id  int,
    student_id int
);

-- INSERT INTO school.groups values ();