from pickle import TRUE
import sys



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
    return matrix_key

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
    # now i got all teh columns in a dictionary
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

    # now the role of this function is done
    # i can lutiply the text input with  the key input and now it's in HEX
    # now am adding a fuction cause there might be some hexes with only one value
    # so am adding 0 before if EX: (A) || (9) => (0A) || (09)
    i=1
    final_hex_col = {1: [], 2: [], 3: [], 4: []}
    column_2 = []
    for key in hexed_columns :
        for elements in hexed_columns[key]:
            if len(elements) == 1:
                print("before"+elements+"\n")
                new_element = "0"
                new_element += elements
                print("after" + new_element + "\n") # now each elements become two chracter
                column_2.append(new_element)
            else:
                column_2.append(elements)
        final_hex_col.update({i: column_2})
        print("the "+str(i)+"column has the new value")
        print(final_hex_col)
        column_2 = []
        i=i+1
    print(final_hex_col)
    return final_hex_col

## now running the functions
user_key = sys.argv[1]
user_text = sys.argv[2]
print("starting the encryption .....")
first = key_exps(user_text)
second = key_exps(user_key)
third = add_Rkey(first, second)
print("the encryption is done ......")
