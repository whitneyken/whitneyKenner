-- MySQL dump 10.13  Distrib 8.0.33, for macos12.6 (arm64)
--
-- Host: cs-db.eng.utah.edu    Database: LMS_Sol
-- ------------------------------------------------------
-- Server version	5.5.5-10.11.3-MariaDB-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Administrators`
--

DROP TABLE IF EXISTS `Administrators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Administrators` (
  `uID` char(8) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `DOB` date NOT NULL,
  PRIMARY KEY (`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrators`
--

LOCK TABLES `Administrators` WRITE;
/*!40000 ALTER TABLE `Administrators` DISABLE KEYS */;
INSERT INTO `Administrators` VALUES ('u0000001','Vicki','Rigby','0001-01-01'),('u0000014','fake','person','2000-01-01');
/*!40000 ALTER TABLE `Administrators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssignmentCategories`
--

DROP TABLE IF EXISTS `AssignmentCategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AssignmentCategories` (
  `CategoryID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Weight` int(10) unsigned NOT NULL,
  `InClass` int(10) unsigned NOT NULL,
  PRIMARY KEY (`CategoryID`),
  UNIQUE KEY `Name` (`Name`,`InClass`),
  KEY `AssignmentCategories_ibfk_1` (`InClass`),
  CONSTRAINT `AssignmentCategories_ibfk_1` FOREIGN KEY (`InClass`) REFERENCES `Classes` (`ClassID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssignmentCategories`
--

LOCK TABLES `AssignmentCategories` WRITE;
/*!40000 ALTER TABLE `AssignmentCategories` DISABLE KEYS */;
INSERT INTO `AssignmentCategories` VALUES (6,'Assignments',40,15),(7,'Quizzes',10,15),(8,'Exams',50,15),(9,'Assignments',30,16),(10,'Project',30,16),(11,'Homework',40,13),(12,'Midterm',15,13),(13,'Labs',10,13),(14,'Final Exam',15,13),(15,'Code Reviews',5,13),(16,'Assignments',30,17),(17,'With Space',10,17);
/*!40000 ALTER TABLE `AssignmentCategories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Assignments`
--

DROP TABLE IF EXISTS `Assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Assignments` (
  `AssignmentID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Contents` varchar(8192) NOT NULL,
  `Due` datetime NOT NULL,
  `MaxPoints` int(10) unsigned NOT NULL,
  `Category` int(10) unsigned NOT NULL,
  PRIMARY KEY (`AssignmentID`),
  UNIQUE KEY `name_unique` (`Name`,`Category`),
  KEY `Assignments_ibfk_1` (`Category`),
  CONSTRAINT `Assignments_ibfk_1` FOREIGN KEY (`Category`) REFERENCES `AssignmentCategories` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Assignments`
--

LOCK TABLES `Assignments` WRITE;
/*!40000 ALTER TABLE `Assignments` DISABLE KEYS */;
INSERT INTO `Assignments` VALUES (6,'Simulator','<p><strong>All assignments in this class will be graded/evaluated on the CADE lab1 machines. Even if your solution works on some other machine, it will receive no credit if it doesn\'t work on CADE lab1.</strong></p>\n<h3>Simulator</h3>\n<p>In this assignment you\'ll be implementing a simulator for a simplified x86 processor. The basic job of a simulator is to mimic a real processor by keeping track (in software) of the various components of a processor such as registers, memory, and which instruction to execute next. As your simulator program runs, it will \"execute\" simulated x86 instructions one at a time, while updating software representations of registers and memory.</p>\n<p>We have not yet covered the x86 instruction set, but the assignment is divided into two parts.&nbsp;We have covered everything needed for Part 1, and you should start on it right away.</p>\n<p>To make our simulator more reasonable, we will only support 32-bit (\"l\") instructions, and the machine code representation of instructions will be of fixed size (32 bits).</p>\n<p>Start by downloading <a class=\"instructure_file_link\" title=\"simulator.tar.gz\" href=\"https://utah.instructure.com/courses/540633/files/84438778/download?wrap=1\" data-api-endpoint=\"https://utah.instructure.com/api/v1/courses/540633/files/84438778\" data-api-returntype=\"File\">simulator.tar.gz</a></p>\n<p>This is a gzipped tar file that can be unpacked by running the following command on a CADE lab1 machine:</p>\n<pre>tar zxvf simulator.tar.gz</pre>\n<p>This will create a new directory called simulator containing a few things:</p>\n<ul>\n<li>simulator.c - This serves as a starting point for your solution.</li>\n<li>instruction.h - This provides some useful definitions for representing instructions.</li>\n<li>tests - This directory contains simple, moderate, and complex tests for your simulator. The tests themselves are machine code programs that your simulator should be able to input and run. <strong>Do not modify any files in this directory. </strong>If you wish to create your own tests, or modify the existing ones, copy them to a different directory first.</li>\n<li>run_tests.sh - This is a bash script that will run all of the tests and report the results.</li>\n<li>assembler - This is a pre-compiled executable that can be used to generate a simulator input machine code file&nbsp;given an assembly file. You do not need to use the assembler, but it may be useful for testing if you want to modify or write your own assembly test programs.</li>\n</ul>\n<p><strong>Compiling the simulator</strong></p>\n<p>To compile the simulator, run one of the following commands:</p>\n<pre>gcc -g simulator.c -o simulator</pre>\n<p>The above will compile with debug symbols, useful during development.</p>\n<pre>gcc -O2 simulator.c -o simulator</pre>\n<p>The above will compile with optimization. <strong>This is what we will use for grading. Make sure your program passes all tests when compiled with optimization.&nbsp;</strong>If your program behaves differently with and without optimization, it most likely means your program has undefined behavior, such as using uninitialized data.&nbsp;</p>\n<p><strong>Running the simulator</strong></p>\n<p>To run the simulator, pass it a command-line argument specifying the machine code binary file to run. The tests directory contains a number of&nbsp;assembly files with extension \".s\" and assembled binary machine code files with extension \".o\". The simulator accepts the \".o\" files as input.</p>\n<p>For example, you can run the simulator with the following command:</p>\n<pre>./simulator tests/simple/subl.o</pre>\n<p>This will cause the simulator to load the \"subl.o\" binary file, which represents the machine encoding of the instructions in the corresponding \".s\" file.</p>\n<p>As-is, the simulator will load the binary file, but that\'s it. It is your job to finish the implementation of the simulator according to the specifications described in this assignment so that it executes (simulates) the provided machine code program.</p>\n<p>The provided assembly files in the tests/ directory are there for your reference and were used to generate the corresponding \".o\" files. <strong>You should not modify the provided tests</strong>, but you can copy them to a different location, then use the provided assembler executable to create your own \".o\" files. See below for instructions on using the assembler.</p>\n<h3>Part 1 - Decode the Binary Machine Code</h3>\n<p>The provided \".o\" files are binary files representing machine code for our simulator. Each instruction is encoded as 4 consecutive bytes (32 bits). For example, take a look at tests/simple/subl.s and tests/simple/subl.o. Notice that there are four instructions in the assembly file, so the binary \".o\" file should contain 16 bytes (4*4). Verify that this is true by looking at the file size using the ls command.</p>\n<pre>ls -l tests/simple/subl.o</pre>\n<p>You should see \"16\" somewhere in the output, along with other information such as the date.</p>\n<p>Our first task is to decode the raw bytes into usable data structures in our simulator. For Part 1, we will only decode the bytes. In Part 2, we will execute the decoded instructions.</p>\n<p>Each 4-byte value in the machine code represents all necessary information for one instruction, such as the opcode and inputs/outputs. Our machine code represents all instructions in the below&nbsp;form. All bits are specified in order of significance.</p>\n<table border=\"-\">\n<tbody>\n<tr>\n<td>Bits:</td>\n<td style=\"text-align: center;\">31 - 27</td>\n<td style=\"text-align: center;\">26 - 22</td>\n<td style=\"text-align: center;\">21 - 17</td>\n<td style=\"text-align: center;\">16</td>\n<td style=\"text-align: center;\">15 - 0</td>\n</tr>\n<tr style=\"text-align: center;\">\n<td style=\"text-align: left;\">Meaning: &nbsp;</td>\n<td>opcode</td>\n<td>reg1</td>\n<td>reg2</td>\n<td>unused</td>\n<td>immediate</td>\n</tr>\n</tbody>\n</table>\n<p>For example, let\'s look at the third instruction in one of the test files:&nbsp;tests/simple/addl_imm_reg.s</p>\n<pre>addl&nbsp; &nbsp; $-2, %ebx</pre>\n<p>This particular form of \"addl\" instruction has an opcode of 2 and \"%ebx\" has a register ID of 1, and the&nbsp;immediate bits should represent -2. Thus, the instruction is encoded with the following bits:</p>\n<p><span style=\"color: #ff0000;\">00010</span><span style=\"color: #008000;\"><span style=\"color: #0000ff;\">00001</span>00000</span>0<span style=\"color: #800080;\">1111111111111110</span></p>\n<p><span style=\"color: #ff0000;\">opcode = 2</span></p>\n<p><span style=\"color: #0000ff;\">reg1 = 1</span></p>\n<p><span style=\"color: #008000;\">reg2 = 0 (unused)</span></p>\n<p><span style=\"color: #800080;\">immediate = -2 (16-bit signed two\'s complement)</span></p>\n<p>The \"reg2\" bits are unused in this instruction since it only uses one register. The unused 0 bit in-between the reg2 and immediate bits will always be unused. See the table below for the full list of opcodes and register IDs.</p>\n<p><strong>instruction_t</strong></p>\n<p>Take a look at the provided \"instruction.h\" file, which provides a struct definition called \"instruction_t\" for representing an instruction. Our task is to&nbsp;create an array of instruction_t and fill in the fields of each one with the appropriate values based on the raw bits encoded in the input file.</p>\n<p>For example, suppose you create an instruction_t variable called \"instr\". After filling in the fields of \"instr\", they should have the following values:</p>\n<pre>instr.opcode = 2<br /><br />instr.first_register = 1<br /><br />instr.second_register = 0<br /><br />instr.immediate = -2</pre>\n<p>You will need to programmatically extract these values out of the 32 bits that encode the instruction using bitwise shifting and masking&nbsp;operations in C. Once you are done, you can uncomment line 73 in simulator.c to print the decoded values. Verify they are correct using the following tables:</p>\n<table border=\"-\">\n<tbody>\n<tr>\n<td>Assembly Name</td>\n<td>Instruction &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>\n<td>Opcode&nbsp;</td>\n</tr>\n<tr>\n<td>subl</td>\n<td>subl</td>\n<td>0</td>\n</tr>\n<tr>\n<td>addl</td>\n<td>addl_reg_reg</td>\n<td>1</td>\n</tr>\n<tr>\n<td>addl</td>\n<td>add','2018-01-01 23:59:59',100,6),(7,'Quiz 1','What is the value of rsp after ...','2018-01-01 23:59:59',10,7),(8,'Quiz 2','How many times will the signal handler run?','2018-01-01 23:59:59',10,7),(9,'HW4 - SQL','Write some SQL queries...','2018-01-01 23:59:59',100,9),(10,'Phase 1 - ER Diagram','Draw an LMS ER diagram.','2018-01-01 23:59:59',100,10),(11,'Expression Evaluator','<div id=\"topics_header\">\n<h2>Infix Expression Evaluation</h2>\n<p>Your task is to write a method that evaluates <strong>integer</strong> arithmetic expressions written using standard infix notation. It should respect the usual precedence rules and integer arithmetic.</p>\n<p>For example, if the input is \"(2 + 3) * 5 + 2\", the result should be 27. If the input is \"(2 + A6) * 5 + 2\", the result of course depends on the value of the variable A6. If A6 is 7, for example, the result should be 47.</p>\n<p>Here is an algorithm for evaluating an infix expression. We\'ll assume that the expression has been broken into a sequence of tokens. The only legal tokens are the four operator symbols: + - * /, left parentheses, right parentheses, non-negative integers, whitespace, and variables consisting of one or more letters followed by one or more digits. <strong>Letters can be lowercase or uppercase.&nbsp;</strong>You can assume your evaluator will not be used with an expression that would cause an int to overflow, if following the algorithm below.</p>\n<p>Begin with two empty stacks: a value stack and an operator stack. Process the tokens from left to right. For each token t:</p>\n<table style=\"border: 1px solid black;\" border=\"2\" cellpadding=\"10\">\n<tbody>\n<tr>\n<td style=\"border: 1px solid black;\"><strong> Condition </strong></td>\n<td style=\"border: 1px solid black;\"><strong> Action </strong></td>\n<td style=\"border: 1px solid black;\"><strong> Possible Errors </strong></td>\n</tr>\n<tr>\n<td style=\"border: 1px solid black;\">t is an integer</td>\n<td style=\"border: 1px solid black;\">\n<p>If * or / is at the top of the operator stack, pop the value stack, pop the operator stack, and apply the popped operator to the popped number and t. Push the result onto the value stack.</p>\n<p>Otherwise, push t onto the value stack.</p>\n</td>\n<td style=\"border: 1px solid black;\">\n<ul>\n<li><span style=\"background-color: transparent;\">The value stack is empty.</span></li>\n<li><span style=\"background-color: transparent;\">A division by zero occurs.</span></li>\n</ul>\n</td>\n</tr>\n<tr>\n<td style=\"border: 1px solid black;\">\n<p>t is a variable</p>\n</td>\n<td style=\"border: 1px solid black;\">Proceed as above, using the looked-up value of t instead of t</td>\n<td style=\"border: 1px solid black;\">\n<ul>\n<li><span style=\"background-color: transparent;\">If looking up t reveals it has no value (the delegate throws).</span></li>\n<li><span style=\"background-color: transparent;\">If the value stack is empty.</span></li>\n<li><span style=\"background-color: transparent;\">A division by zero occurs.</span></li>\n</ul>\n</td>\n</tr>\n<tr>\n<td style=\"border: 1px solid black;\">t is + or -</td>\n<td style=\"border: 1px solid black;\">\n<p>If + or - is at the top of the operator stack, pop the value stack twice and the operator stack once, then apply the popped operator to the popped numbers, then push the result onto the value stack.</p>\n<p>Push t onto the operator stack</p>\n</td>\n<td style=\"border: 1px solid black;\">\n<ul>\n<li><span style=\"background-color: transparent;\">The value stack contains fewer than 2 values if trying to pop it.<br /></span></li>\n</ul>\n</td>\n</tr>\n<tr>\n<td style=\"border: 1px solid black;\">t is * or /</td>\n<td style=\"border: 1px solid black;\">Push t onto the operator stack</td>\n<td style=\"border: 1px solid black;\"></td>\n</tr>\n<tr>\n<td>t is a left parenthesis \"(\"</td>\n<td style=\"border: 1px solid black;\">Push t onto the operator stack</td>\n<td style=\"border: 1px solid black;\"></td>\n</tr>\n<tr>\n<td style=\"border: 1px solid black;\">t is a right parenthesis \")\"</td>\n<td style=\"border: 1px solid black;\">\n<p>If + or - is at the top of the operator stack, pop the value stack twice and the operator stack once. Apply the popped operator to the popped numbers. Push the result onto the value stack.</p>\n<p>Next, the top of the operator stack should be a \'(\'. Pop it.</p>\n<p>Finally, if * or / is at the top of the operator stack, pop the value stack twice and the operator stack once. Apply the popped operator to the popped numbers. Push the result onto the value stack.</p>\n</td>\n<td style=\"border: 1px solid black;\">\n<ul>\n<li><span style=\"background-color: transparent;\">The value stack contains fewer than 2 values during the first step</span></li>\n<li><span style=\"background-color: transparent;\">A \'(\' isn\'t found where expected</span></li>\n<li><span style=\"background-color: transparent;\">The value stack contains fewer than 2 values during the final step</span></li>\n<li><span style=\"background-color: transparent;\">A division by zero occurs<br /></span></li>\n</ul>\n</td>\n</tr>\n</tbody>\n</table>\n<p>When the last token has been processed:</p>\n<table style=\"border: 1px solid black;\" border=\"2\" cellpadding=\"10\">\n<tbody>\n<tr>\n<td style=\"border: 1px solid black;\"><strong> Condition </strong></td>\n<td style=\"border: 1px solid black;\"><strong> Action </strong></td>\n<td style=\"border: 1px solid black;\"><strong> Possible errors </strong></td>\n</tr>\n<tr>\n<td style=\"border: 1px solid black;\">Operator stack is empty</td>\n<td style=\"border: 1px solid black;\">\n<p>Value stack should contain a single number</p>\n<p>Pop it and report as the value of the expression</p>\n</td>\n<td style=\"border: 1px solid black;\">\n<ul>\n<li><span style=\"background-color: transparent;\">There isn\'t exactly one value on the value stack</span></li>\n</ul>\n</td>\n</tr>\n<tr>\n<td style=\"border: 1px solid black;\">Operator stack is not empty</td>\n<td style=\"border: 1px solid black;\">There should be exactly one operator on the operator stack, and it should be either + or -. There should be exactly two values on the value stack. Apply the operator to the two values and report the result as the value of the expression.</td>\n<td style=\"border: 1px solid black;\">\n<ul>\n<li><span style=\"background-color: transparent;\">There isn\'t exactly one operator on the operator stack or exactly two numbers on the value stack</span></li>\n</ul>\n</td>\n</tr>\n</tbody>\n</table>\n<h2>Begin Writing Your Library</h2>\n<p>The first thing you should do is put the following delegate declaration and method into your class:</p>\n<pre class=\"code\"><code class=\"csharp\">\n      public delegate int Lookup(String v);\n      \n      public static int Evaluate(String exp, Lookup variableEvaluator)\n      {\n        // TODO...\n      }\n    </code></pre>\n<p>Evaluate takes two parameters. The first is the expression to be evaluated, and the second is a delegate that you can use to look up the value of a variable. Given a variable name as its parameter, the delegate will either return an int (the value of the variable) or throw an ArgumentException (if the variable has no value). <strong>You, as the author of your FormulaEvaluator library, do not need to write the delegate, only utilize it. </strong>You will provide delegates later when you take on the role of tester.</p>\n<p>The method should evaluate the expression, using the algorithm above. It should return the value of the expression if it has a value, or throw an ArgumentException if any of the possible errors from the algorithm occurs.</p>\n<p>To make your life easier, here is a line of code that will split a string into tokens, which you can then analyze using the algorithm above:</p>\n<pre class=\"code\"><code class=\"csharp\">\n    string[] substrings = Regex.Split(s, \"(\\\\()|(\\\\))|(-)|(\\\\+)|(\\\\*)|(/)\");\n    </code></pre>\n<p>For example, if s is the string \"(2+35)*A7\", it will return the array</p>\n<p>[\"(\", \"2\", \"+\", \"35\", \")\", \"*\", \"A7\"]</p>\n<p><strong>There will probably be some empty strings mixed in. You should ignore those.</strong></p>\n<p>If the string contains whitespace, such as \"(2 + 35) * A7\", it will return the array</p>\n<p>[\"(\", \"2 \", \"+\", \" 35\", \")\", \" \", \"*\", \" A7\"]</p>\n<p><strong>Ignore leading and trailing whitespace in each token.</strong></p>\n<p>&nbsp;In a legal expression, the only possible tokens after trimming surrounding whitespace are (, ), +, -, *, /, non-negative integers, and strings that begin with one or more letters and end with one or more digits. If your method detects any other kind of token (other than an empty string), it should throw an ArgumentException.</p>\n<hr />\n<h2>Individual and Original Work</h2>\n<p>This is an individual ','2018-01-01 23:59:59',100,11),(12,'Midterm Exam','(no contents)','2018-01-01 23:59:59',100,12),(13,'Lab 1','Setup VS','2018-01-01 23:59:59',10,13),(14,'Lab 2','Setup git','2018-01-01 23:59:59',10,13),(15,'Final Exam','(no contents)','2018-01-01 23:59:59',100,14),(16,'Review Homework 1','Review a classmate\'s homework 1','2018-01-01 23:59:59',5,15),(17,'Assignment #2','Testing','2018-01-01 23:59:59',100,16),(18,'A1','test','2018-01-01 23:59:59',100,17),(19,'Review Homework 2','Review someone else\'s homework 2','2018-01-01 23:59:59',10,15),(21,'HW1 - Relational Model','<p>In this assignment, we will practice reasoning about relations and keys.</p>\n<p><strong>You will hand in a pdf of your solutions for this assignment on gradescope.</strong> Most of your answers will be simple text, but you will also need a few tables. Use any text editor you want that supports inserting tables and converting to pdf.&nbsp;</p>\n<h3>Part 1 - Car Dealership</h3>\n<p>Consider a database for a car dealership. A car has a make, model, year, color, and vehicle identification number (VIN). A salesperson has a name and a social security number (SSN), and is responsible for trying to sell zero or more cars. A car dealership has an inventory of cars and a group of salespeople. It needs to keep track of which car(s) each salesperson is trying to sell. More than one salesperson can be assigned to any given car, but a car does not necessarily have any salespeople assigned to it. The schemas for this database are below.</p>\n<p>Cars:</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n    <tbody>\n        <tr>\n            <td style=\"width: 20%;\">\n                <p>VIN (int)</p>\n                <p>PK</p>\n            </td>\n            <td style=\"width: 20%;\">\n                <p>make (string)</p>\n                <p>&nbsp;</p>\n            </td>\n            <td style=\"width: 20%;\">\n                <p>model (string)</p>\n                <p>&nbsp;</p>\n            </td>\n            <td style=\"width: 20%;\">\n                <p>year (int)</p>\n                <p>&nbsp;</p>\n            </td>\n            <td style=\"width: 20%;\">\n                <p>color (string)</p>\n                <p>&nbsp;</p>\n            </td>\n        </tr>\n    </tbody>\n</table>\n<p>Salespeople:</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n    <tbody>\n        <tr>\n            <td style=\"width: 50%;\">\n                <p>SSN (int)</p>\n                <p>PK</p>\n            </td>\n            <td style=\"width: 50%;\">\n                <p>name (string)</p>\n                <p>&nbsp;</p>\n            </td>\n        </tr>\n    </tbody>\n</table>\n<p>Selling:</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n    <tbody>\n        <tr>\n            <td style=\"width: 50%;\">\n                <p>SSN (int)</p>\n                <p>PK</p>\n            </td>\n            <td style=\"width: 50%;\">\n                <p>VIN (int)</p>\n                <p>PK</p>\n            </td>\n        </tr>\n    </tbody>\n</table>\n<p>The name and type of each attribute is given, and \"PK\" indicates the attribute is part of the primary key. For the \"Selling\" schema, {SSN, VIN} is the primary key.</p>\n<p><strong>Problem 1a)</strong> Create and fill in instance tables for each of these schemas such that they represent the information described below. Some of the information is not specified, and you will need to make up values with appropriate types that fit the relational model. Only make up data if it is needed based on the existing information. You do not need to make up real VINs or SSNs, as long as the number you pick has the right type. For example, 0 is considered a valid VIN, because it is an integer. None of your fields should be empty or null.</p>\n<p>Cars:</p>\n<ul>\n    <li>Red Toyota Tacoma, 2008</li>\n    <li>Green Toyota Tacoma, 1999</li>\n    <li>White Tesla Model 3, 2018</li>\n    <li>Blue Subaru WRX, 2016</li>\n    <li>Red Ford F150, 2004</li>\n</ul>\n<p>Salespeople:</p>\n<ul>\n    <li>Arnold, trying to sell all Toyotas</li>\n    <li>Jessica, trying to sell all red cars</li>\n    <li>Steve, trying to sell the Tesla</li>\n</ul>\n<p><strong>Problem 1b)</strong> If the primary key of the \"Selling\" schema was just {SSN}, briefly describe how this would change the car dealership. Note that the instances you created in part a) may not be compatible with this change.</p>\n<p><strong>Problem 1c)</strong> If the primary key of the \"Selling\" schema was just {VIN}, briefly describe how this would change the car dealership. Note that the instances you created in part a) may not be compatible with this change.</p>\n<h3>Part 2 - Keys and Superkeys</h3>\n<p>Consider the following relation:</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n    <tbody>\n        <tr style=\"height: 24px;\">\n            <td style=\"width: 33.3333%; height: 24px;\"><strong>A1</strong></td>\n            <td style=\"width: 33.3333%; height: 24px;\"><strong>A2</strong></td>\n            <td style=\"width: 33.3333%; height: 24px;\"><strong>A3</strong></td>\n        </tr>\n        <tr style=\"height: 24px;\">\n            <td style=\"width: 33.3333%; height: 24px;\">x</td>\n            <td style=\"width: 33.3333%; height: 24px;\">4.0</td>\n            <td style=\"width: 33.3333%; height: 24px;\">q</td>\n        </tr>\n        <tr style=\"height: 24px;\">\n            <td style=\"width: 33.3333%; height: 24px;\">y</td>\n            <td style=\"width: 33.3333%; height: 24px;\">4.0</td>\n            <td style=\"width: 33.3333%; height: 24px;\">p</td>\n        </tr>\n        <tr style=\"height: 24px;\">\n            <td style=\"width: 33.3333%; height: 24px;\">z</td>\n            <td style=\"width: 33.3333%; height: 24px;\">3.1</td>\n            <td style=\"width: 33.3333%; height: 24px;\">p</td>\n        </tr>\n        <tr>\n            <td style=\"width: 33.3333%;\">z</td>\n            <td style=\"width: 33.3333%;\">4.0</td>\n            <td style=\"width: 33.3333%;\">p</td>\n        </tr>\n    </tbody>\n</table>\n<p><strong>Problem 2)</strong> Fill in the missing entries in the table below. For the \"Superkey?\" and \"Key?\" columns, write \"no\" if you can rule-out that possibility for the given attribute set, and write \"?\" if you can\'t. Make sure to include the empty set in the Proper Subsets, but remember the empty set is not considered a superkey. Some cells have been given as an example.</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n    <tbody>\n        <tr>\n            <td style=\"width: 25%;\">Attribute Sets</td>\n            <td style=\"width: 25%;\">Superkey?</td>\n            <td style=\"width: 25%;\">Proper Subsets</td>\n            <td style=\"width: 25%;\">Key?</td>\n        </tr>\n        <tr>\n            <td style=\"width: 25%;\">{A1}</td>\n            <td style=\"width: 25%;\">No</td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n        </tr>\n        <tr>\n            <td style=\"width: 25%;\">{A2}</td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n        </tr>\n        <tr>\n            <td style=\"width: 25%;\">{A3}</td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n        </tr>\n        <tr>\n            <td style=\"width: 25%;\">{A1, A2}</td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n        </tr>\n        <tr>\n            <td style=\"width: 25%;\">{A1, A3}</td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n        </tr>\n        <tr>\n            <td style=\"width: 25%;\">{A2, A3}</td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\">{A2}, {A3}, {}</td>\n            <td style=\"width: 25%;\"></td>\n        </tr>\n        <tr>\n            <td style=\"width: 25%;\">{A1, A2, A3}</td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n            <td style=\"width: 25%;\"></td>\n        </tr>\n    </tbody>\n</table>\n<h3>Part 3 - Picking Keys</h3>\n<p><strong>Note: The previous problem is \"backwards\" from how database design works. We do not start with data, we start by picking keys that implement the problem description.</strong></p>\n<p>Consider the <a class=\"instructure_file_link instructure_scribd_file\" title=\"library.pdf\" href=\"https://utah.instructure.com/courses/600418/files/96338979/download?wrap=1\" data-api-endpoint=\"https://utah.instructure.com/api/v1/courses/600418/files/96338979\" data-api-returntype=\"File\">Library database</a> from class...\n</p>','2018-01-01 23:59:59',100,9);
/*!40000 ALTER TABLE `Assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Classes`
--

DROP TABLE IF EXISTS `Classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Classes` (
  `ClassID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Season` varchar(6) NOT NULL,
  `Year` int(10) unsigned NOT NULL,
  `Location` varchar(100) NOT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL,
  `Listing` int(10) unsigned NOT NULL,
  `TaughtBy` char(8) DEFAULT NULL,
  PRIMARY KEY (`ClassID`),
  UNIQUE KEY `Season` (`Season`,`Year`,`Listing`),
  KEY `Classes_ibfk_1` (`Listing`),
  KEY `Taught` (`TaughtBy`),
  CONSTRAINT `Classes_ibfk_1` FOREIGN KEY (`Listing`) REFERENCES `Courses` (`CatalogID`),
  CONSTRAINT `Taught` FOREIGN KEY (`TaughtBy`) REFERENCES `Professors` (`uID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Classes`
--

LOCK TABLES `Classes` WRITE;
/*!40000 ALTER TABLE `Classes` DISABLE KEYS */;
INSERT INTO `Classes` VALUES (10,'Spring',2020,'M LI 1745','11:50:00','13:10:00',5,'u0000002'),(11,'Fall',2018,'ASB 220','15:00:00','16:20:00',7,'u0000007'),(12,'Spring',2020,'WEB L104','14:00:00','15:20:00',10,'u0000008'),(13,'Fall',2019,'WEB L104','14:00:00','15:20:00',10,'u0000007'),(14,'Fall',2019,'WEB L101','13:25:00','14:45:00',9,'u0000009'),(15,'Spring',2019,'WEB L105','15:00:00','16:20:00',9,'u0000007'),(16,'Spring',2020,'WEB L104','11:50:00','13:10:00',8,'u0000007'),(17,'Spring',2019,'MEK 3550','11:50:00','13:10:00',8,'u0000007'),(18,'Spring',2020,'AEB 350','08:00:00','17:00:00',11,'u0000004'),(19,'Spring',2020,'WEB L112','09:40:00','22:30:00',13,'u0000005'),(20,'Spring',2020,'WEB L112','07:30:00','08:20:00',15,'u0000006');
/*!40000 ALTER TABLE `Classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Courses`
--

DROP TABLE IF EXISTS `Courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Courses` (
  `CatalogID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Number` int(10) unsigned NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Department` varchar(4) NOT NULL,
  PRIMARY KEY (`CatalogID`),
  UNIQUE KEY `Number` (`Number`,`Department`),
  KEY `Courses_ibfk_1` (`Department`),
  CONSTRAINT `Courses_ibfk_1` FOREIGN KEY (`Department`) REFERENCES `Departments` (`Subject`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Courses`
--

LOCK TABLES `Courses` WRITE;
/*!40000 ALTER TABLE `Courses` DISABLE KEYS */;
INSERT INTO `Courses` VALUES (4,2740,'Digital Imaging','ART'),(5,2060,'Digital Photography','ART'),(6,1610,'Fundamentals of Biology','BIOL'),(7,2420,'Intro to Algorithms and Data Structures','CS'),(8,5530,'Database Systems','CS'),(9,4400,'Computer Systems','CS'),(10,3500,'Software Practice I','CS'),(11,1010,'Dance in Culture','DANC'),(12,1010,'Intermediate Algebra','MATH'),(13,1210,'Calculus I','MATH'),(14,2270,'Linear Algebra','MATH'),(15,2210,'Physics for Scientists and Engineers I','PHYS'),(16,2220,'Physics for Scientists and Engineers II','PHYS'),(17,5590,'High Energy Astrophysics','PHYS');
/*!40000 ALTER TABLE `Courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Departments`
--

DROP TABLE IF EXISTS `Departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Departments` (
  `Name` varchar(100) NOT NULL,
  `Subject` varchar(4) NOT NULL,
  PRIMARY KEY (`Subject`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departments`
--

LOCK TABLES `Departments` WRITE;
/*!40000 ALTER TABLE `Departments` DISABLE KEYS */;
INSERT INTO `Departments` VALUES ('Art','ART'),('Biology','BIOL'),('School of Computing','CS'),('School of Dance','DANC'),('History','HIST'),('Mathematics','MATH'),('Physics','PHYS');
/*!40000 ALTER TABLE `Departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enrolled`
--

DROP TABLE IF EXISTS `Enrolled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Enrolled` (
  `Student` char(8) NOT NULL,
  `Class` int(10) unsigned NOT NULL,
  `Grade` varchar(2) NOT NULL,
  PRIMARY KEY (`Student`,`Class`),
  KEY `Enrolled_ibfk_2` (`Class`),
  CONSTRAINT `Enrolled_ibfk_1` FOREIGN KEY (`Student`) REFERENCES `Students` (`uID`),
  CONSTRAINT `Enrolled_ibfk_2` FOREIGN KEY (`Class`) REFERENCES `Classes` (`ClassID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enrolled`
--

LOCK TABLES `Enrolled` WRITE;
/*!40000 ALTER TABLE `Enrolled` DISABLE KEYS */;
INSERT INTO `Enrolled` VALUES ('u0000010',13,'C+'),('u0000010',16,'A-'),('u0000010',18,'--'),('u0000010',19,'--'),('u0000015',10,'--'),('u0000015',13,'E'),('u0000015',19,'--');
/*!40000 ALTER TABLE `Enrolled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Professors`
--

DROP TABLE IF EXISTS `Professors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Professors` (
  `uID` char(8) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `DOB` date NOT NULL,
  `WorksIn` varchar(4) NOT NULL,
  PRIMARY KEY (`uID`),
  KEY `Professors_ibfk_1` (`WorksIn`),
  CONSTRAINT `Professors_ibfk_1` FOREIGN KEY (`WorksIn`) REFERENCES `Departments` (`Subject`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Professors`
--

LOCK TABLES `Professors` WRITE;
/*!40000 ALTER TABLE `Professors` DISABLE KEYS */;
INSERT INTO `Professors` VALUES ('u0000002','Erika','Cespedes','0001-01-01','ART'),('u0000003','David','Blair','0001-01-01','BIOL'),('u0000004','Jasmine','Stack','0001-01-01','DANC'),('u0000005','Benjamin','Briggs','0001-01-01','MATH'),('u0000006','Shanti','Deemyad','0001-01-01','PHYS'),('u0000007','Daniel','Kopta','0001-01-01','CS'),('u0000008','Jim','de St. Germain','0001-01-01','CS'),('u0000009','Erin','Parker','0001-01-01','CS');
/*!40000 ALTER TABLE `Professors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Students`
--

DROP TABLE IF EXISTS `Students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Students` (
  `uID` char(8) NOT NULL,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `DOB` date NOT NULL,
  `Major` varchar(4) NOT NULL,
  PRIMARY KEY (`uID`),
  KEY `Students_ibfk_1` (`Major`),
  CONSTRAINT `Students_ibfk_1` FOREIGN KEY (`Major`) REFERENCES `Departments` (`Subject`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Students`
--

LOCK TABLES `Students` WRITE;
/*!40000 ALTER TABLE `Students` DISABLE KEYS */;
INSERT INTO `Students` VALUES ('u0000010','James','Hetfield','0001-01-01','CS'),('u0000011','Salvador','Dali','0001-01-01','ART'),('u0000015','Salvador','Dali','0001-01-01','ART'),('u0000016','Eagle','Eye','2020-01-02','ART'),('u0000017','V','S','0001-01-01','ART'),('u0000018','V','S','0001-01-01','ART'),('u0000019','vs','vs','0001-01-01','CS');
/*!40000 ALTER TABLE `Students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Submissions`
--

DROP TABLE IF EXISTS `Submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Submissions` (
  `Assignment` int(10) unsigned NOT NULL,
  `Student` char(8) NOT NULL,
  `Score` int(10) unsigned NOT NULL,
  `SubmissionContents` varchar(8192) DEFAULT NULL,
  `Time` datetime NOT NULL,
  PRIMARY KEY (`Assignment`,`Student`),
  KEY `Submissions_ibfk_2` (`Student`),
  CONSTRAINT `Submissions_ibfk_1` FOREIGN KEY (`Assignment`) REFERENCES `Assignments` (`AssignmentID`),
  CONSTRAINT `Submissions_ibfk_2` FOREIGN KEY (`Student`) REFERENCES `Students` (`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Submissions`
--

LOCK TABLES `Submissions` WRITE;
/*!40000 ALTER TABLE `Submissions` DISABLE KEYS */;
INSERT INTO `Submissions` VALUES (9,'u0000010',80,'SELECT * FROM ...','2020-02-22 13:37:19'),(10,'u0000010',100,'A -- B','2021-03-05 14:07:29'),(11,'u0000010',95,'foreach(...)','2020-02-22 13:42:54'),(11,'u0000015',3,'melting clocks','2020-02-22 14:07:45'),(12,'u0000010',99,'use a delegate','2020-02-22 13:43:41'),(13,'u0000010',10,'VS setup is done','2020-02-22 13:57:18'),(14,'u0000010',10,'here is my git commit ID: 76ab4fa','2020-02-22 13:57:58'),(15,'u0000010',0,'pi','2020-04-20 14:20:52'),(16,'u0000010',10,'They had some pretty good coding practices...','2020-02-22 13:58:37'),(18,'u0000010',0,'submit','2020-04-18 00:13:25'),(21,'u0000010',90,'some new submission','2021-03-05 14:18:06');
/*!40000 ALTER TABLE `Submissions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-26 14:38:26
