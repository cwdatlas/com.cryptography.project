"""
this code is about encryption
until now i have taken an input text and an input key transformed them to matrices then into separate
columns them multiplyed them and finaly changed them to hex
I am now left with sbox and shifting columns
"""
from prettytable import PrettyTable

# i will take a key of fixed 16 bytes which is 16 char
# with a plain text of multiples of fixed 16 bytes blocks
user_key = input("Please insert the key\n ")
# later check the input if its 16 char
# converting input to ascii
# below is an input text
file_input = open("text.txt", "r")
user_text = file_input.read(16) # making sure we are only taking a block of 16 byte

# print(user_text)


def to_ascii(input):
    values = [ord(character) for character in input]
    print("the inputtext changing to ascii:\n")
    return values


def to_matrix(ascii_values):# used to transfrom ascii list to matrix of 4*4
    mat = []
    while ascii_values != []:
        mat.append(ascii_values[:4])
        ascii_values = ascii_values[4:]
    print("the text ascii changing to a matrix:\n")
    return mat


def key_exps(user_key): #this function turns the 16 bytes to a matrix

    asci_key = to_ascii(user_key)
    print("the value of key in ascii is\n")
    print(asci_key)
    # now we are going to turn the list to 4*4 matrix
    matrix_key = to_matrix(asci_key)
    print("the values of the ascii as a matrix\n")
    print(matrix_key)


def mat_tooColumn(input_matrix, col_num): # this function basically return a specific column from matrix
    column = []
    for row in input_matrix:
        column.append(row[col_num])
    return column


def too_hex(new_matrix):
    list_of_colomns = {1: [], 2: [], 3: [], 4: []}
    j=1
    column = []
    #print("converting to HEX....\n")
    for key in new_matrix:
        for i in new_matrix[key]:
            i = format(i, 'x')
            column.append(i)

        list_of_colomns.update({j:column})
        j=j+1
        column =[]
    return list_of_colomns




# now i should create the add round key function
# starting by puting every verctor XOR with the third vector of the key


def add_Rkey(text_matrix,key_matrix):
    # basically this part seperate the text matric into columns

    list_of_colomns = {1: [], 2: [], 3: [], 4: []}
    i=0
    j=1
    column = []
    # now i should change the ascii ti hex
    for j in list_of_colomns:
        for row in text_matrix:
            column.append(row[i])
        i=i+1

        print("this is colum number ")
        print(j)
        print("the values are:")
        print(column)

        list_of_colomns.update({j:column})
        j=+1
        column = []
    print("the list of columns after converting from matrix to columns\n")
    print(list_of_colomns)
    print("\t****************\t\n")
    # now i got all the columns in a dictionary
    # now multiplying all the columns with the key
    j=0
    key_column = mat_tooColumn(key_matrix, 3)
    print("the key matrix value is:")
    print(key_column)
    for j in list_of_colomns:
        result = list(a ^ b for a,b in zip(list_of_colomns[j], key_column))
        list_of_colomns.update({j: result})
        j = +1
        result = []
    print("the new columns values after the mult is :")
    print(list_of_colomns)
    # now i finished the XOR of the whole data block of colums of the input with one key vector
    # now i should change the ascii to hex
    print("the new values of the coloumns after being hexed")
    print(too_hex(list_of_colomns))
    hexed_columns = too_hex(list_of_colomns)
    return hexed_columns
    # now the role of this function is done
    # i can lutiply the text input with  the key input and now it's in HEX


def s_box(col_hex_list):
    # creating the S-box
    myTable = PrettyTable(["", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"])
    myTable.add_row(["0", "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"])
    myTable.add_row(["1", "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "CD"])
    myTable.add_row(["2", "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"])
    myTable.add_row(["3", "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"])
    myTable.add_row(["4", "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"])
    myTable.add_row(["5", "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"])
    myTable.add_row(["6", "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"])
    myTable.add_row(["7", "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"])
    myTable.add_row(["8", "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"])
    myTable.add_row(["9", "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"])
    myTable.add_row(["A", "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"])
    myTable.add_row(["B", "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"])
    myTable.add_row(["C", "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"])
    myTable.add_row(["D", "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"])
    myTable.add_row(["E", "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"])
    myTable.add_row(["F", "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"])
    print(myTable)
    myTable.border = False
    myTable.header = False
    print(myTable.get_string(start=1, end=2) and myTable.get_string(fields=["0", "1"]))



# here i am calling the addR_key fuction with the correct parameters
"""
first = to_ascii(user_text)
first = to_matrix(first)
second = to_ascii(user_key)
second = to_matrix(second)
"""
#print(first)
#list_of_col = {1: [116, 104, 105, 115], 2: [32, 105, 115, 32], 3: [97, 32, 116, 101], 4: [115, 116, 32, 105]}
#too_hex(list_of_col)

# print(second)

#add_Rkey(first, second)
#this one is to call the function that separates the columns from matrix and does the XOR of the key and text input


# key_exps(user_key)
s_box(1)
