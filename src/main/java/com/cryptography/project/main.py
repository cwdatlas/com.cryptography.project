"""
this code is about encryption
until now i have taken an input text and an input key transformed them to matrices then into separate
columns them multiplyed them and finaly changed them to hex
I am now left with sbox and shifting columns
"""
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




# here i am calling the addR_key fuction with the correct parameters
first = to_ascii(user_text)
first = to_matrix(first)
second = to_ascii(user_key)
second = to_matrix(second)

#print(first)
#list_of_col = {1: [116, 104, 105, 115], 2: [32, 105, 115, 32], 3: [97, 32, 116, 101], 4: [115, 116, 32, 105]}
#too_hex(list_of_col)

# print(second)

add_Rkey(first, second)
#this one is to call the function that separates the columns from matrix and does the XOR of the key and text input


# key_exps(user_key)

