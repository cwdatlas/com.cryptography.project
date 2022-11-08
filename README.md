# com.cryptography.project
Written by Aidan Scott
Cryptography project 

Greeings:
  hello, This program is build directly off of the AES ecryption method with a couple changes. The Interface will allow you to easily encrypt a file, see it on the terminal and then the file will be decrypted. The original file will be updated to reflect the decrypted text. It is not perfect. There is a wrong character round every 12 characters. I got help developing some of the cryptographic fuctions basing them off of the funcitons my teammate made in the main.py file. He has developed his own cryptographic algorithm all in python. main.py is not activly used in this project, I keep it there for postarity.

Security:
  The security of this application looks to be modest with our testing. When the encrypted text is zipped, the total file size is 75-85% of the total ecrypted file size
  The compression rate for unencrytped text is around 30% of the original file. This shows that the ecrypted text is relitivly random making a better encryption.
  
Speed:
  The speed of the application is the strongest attrebute. After 300 Thousand characters the time becomes exponential to encrypt and decrypt. I believe the reason for 
  this is because of reading, writing the file. These functions, and others, I believe werent built for large strings. Overall to increase speeds and work loads we
  would need to use smaller strings and other methods better suited for large data sets. Our largest encryption then decryption was lotr.txt (Lord of the rings) 
  3.3 million characters or so. This took round 140 seconds to process while my computer was plugged in on turbo mode. It has a 4900HS round 4.8Gz. More normal on
  a more average setting (proformance while not plugged in) we had texts of random langth get processed under 300 thousand characters creating a line with a constent
  slope. Overall very fast at the cost of accuracy.

The manual can be displayed within the program by typing in 'manual' at the command line.
	--This is the manual for AES clone--
encrypt:
	This command will require you to
	input a key and the location of
	any file on the computer. The 
	key will have to be 16 bytes, 
	which means that its 16 characters
	long. If key isnt 16 bytes then 
	a preset 16 byte key will be used.
	The encrypted text will
	replace all text on the target
	text file.
	Decryption happens after encryption
	happens. This is because we cant
	reliably save the ciphertext as
	it has characters a text file cant
	have saved to it. The Decryption
	method is the exact opposite of 
	the encryption method.

manual:
	explains the function of each
	command.
	
stop:
	Stop will shutdown the program
	
Info:
	We used unit tests to calculate time
	taken to complete each test. The
	user interface here is built for
	interactivity rather than for testing.
	The Ecryption isnt perfect, as there are
	a couple characters that arent encrypted
	properly. We didnt have time to depug this
	issue, but we think it has something to do
	with the substitution.
	
