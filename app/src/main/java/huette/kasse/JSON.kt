package huette.kasse

import java.io.IOException

import java.io.FileWriter

import java.util.ArrayList

import com.google.gson.reflect.TypeToken

import com.google.gson.GsonBuilder

import com.google.gson.Gson

import java.io.File
import java.io.Reader
import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Path

import java.nio.file.Paths




class JSON {

    //private String dirPath 			= System.getProperty("user.dir") + "/JSON/";
    private val dirPath = "/home/pi" + "/JSON/"
    private val fileNameUsers = "users.json"
    private val fileNameDrinks = "drinks.json"
    private val filePathUsers: Path = Paths.get(dirPath + fileNameUsers)
    private val filePathDrinks: Path = Paths.get(dirPath + fileNameDrinks)
    private val folder = File(dirPath)
    private val fileUsers = File(dirPath + fileNameUsers)
    private val fileDrinks = File(dirPath + fileNameDrinks)

    //private GsonBuilder gsonBuilder = new GsonBuilder();
    private val gson = GsonBuilder().setPrettyPrinting().create()

    //private String jsonDaten 		= "";
    private var fw: FileWriter? = null
    private val users = ArrayList<User>()
    private val drinks = ArrayList<Drink>()
    private var readerUsers: Reader? = null
    private var readerDrinks: Reader? = null
    private val userType: Type = object : TypeToken<List<User?>?>() {}.type
    private val drinkType: Type = object : TypeToken<List<Drink?>?>() {}.type

    fun JSON() {
        checkPath()
    }

    fun checkPath() {
        if (!folder.exists()) {
            try {
                Files.createDirectory(filePathUsers.getParent())
                System.out.println("Folder creater: " + filePathUsers.getParent())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        checkUsersPath()
        checkDrinksPath()
        setJSON(users, drinks)
    }

    fun getUsers(): ArrayList<User>? {
        for (i in users.indices) {
            System.out.println("In JSON ist Nutzer " + users[i].fullName)
        }
        return users
    }

    fun getDrinks(): ArrayList<Drink>? {
        return drinks
    }

    private fun checkUsersPath() {
        if (!fileUsers.exists()) {
            try {
                Files.createFile(filePathUsers)
                println("File created: $filePathUsers")
            } catch (e: IOException) {
            }
        } else {
            try {
                readerUsers = Files.newBufferedReader(filePathUsers)
                // In Array lesen war einfacher, dann anschließend in ArrayList users
                val usersList: List<User> = gson.fromJson(readerUsers, userType)
                for (i in usersList.indices) {
                    users.add(usersList[i])
                }
                //User[] userArray = gson.fromJson(reader, User[].class);
                /*if (userArray != null) {
					for (int i = 0; i < userArray.length; i++) {
						// Hier Code zum aus Json importieren
						//users.add(userArray[i]);
						//addUser(userArray[i].getFirstName(), userArray[i].getLastName());
					}
				}*/
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun checkDrinksPath() {
        if (!fileDrinks.exists()) {
            try {
                Files.createFile(filePathDrinks)
                println("File created: $filePathDrinks")
            } catch (e: IOException) {
            }
        } else {
            try {
                readerDrinks = Files.newBufferedReader(filePathDrinks)
                // In Array lesen war einfacher, dann anschließend in ArrayList users
                val drinksList: List<Drink> = gson.fromJson(readerDrinks, drinkType)
                for (i in drinksList.indices) {
                    drinks.add(drinksList[i])
                }
                //User[] userArray = gson.fromJson(reader, User[].class);
                /*if (userArray != null) {
					for (int i = 0; i < userArray.length; i++) {
						// Hier Code zum aus Json importieren
						//users.add(userArray[i]);
						//addUser(userArray[i].getFirstName(), userArray[i].getLastName());
					}
				}*/
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun setJSON(users: ArrayList<User?>?) {
        try {
            fw = FileWriter(fileUsers)
            fw!!.write(gson.toJson(users))
            fw!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun setJSON(users: ArrayList<User>?, drinks: ArrayList<Drink>?) {
        // Benutzer in Json schreiben
        try {
            fw = FileWriter(fileUsers)
            fw!!.write(gson.toJson(users))
            fw!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Drinks in Json schreiben
        try {
            fw = FileWriter(fileDrinks)
            fw!!.write(gson.toJson(drinks))
            fw!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}