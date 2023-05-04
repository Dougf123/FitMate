package com.example.applicationfitmate.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteException

/*Used Database helper form mobile application development to help*/

/* Database Config */
private val DataBaseName = "FitMateDatabase.db"
private val ver: Int = 1

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DataBaseName,null,ver){

    /* Tables */
    /*User Table */
    private val UseTableName = "User"
    private val UseColumn_ID = "ID"
    private val UseColumn_FName = "FirstName"
    private val UseColumn_LName = "LastName"
    private val UseColumn_Email = "Email"
    private val UseColumn_Login = "LoginName"
    private val UseColumn_Pass = "Password"
    private val UseColumn_Age = "Age"
    private val UseColumn_Height = "Height"
    private val UseColumn_Weight = "Weight"

    /* Workout Table */
    private val WorTableName = "Workout"
    private val WorColumn_ID = "ID"
    private val WorColumn_Name = "Name"
    private val WorColumn_Desc = "Description"
    private val WorColumn_Musc = "MuscleGroupID"
    private val WorColumn_Diff = "Difficulty"

    /* Muscle Group Table */
    private val MusTableName = "MuscleGroup"
    private val MusColumn_ID = "ID"
    private val MusColumn_Name = "Name"

    /* Exercise Table */
    private val ExTableName = "Exercise"
    private val ExColumn_ID = "ID"
    private val ExColumn_Name = "Name"
    private val ExColumn_Instr = "Instruction"
    private val ExColumn_MuscID = "MuscleGroupID"

    /* Exercise Workout Table */
    private val ExWorTableName = "ExerciseWorkout"
    private val ExWorColumn_ID = "ID"
    private val ExWorColumn_Name = "Name"
    private val ExWorColumn_Instr = "Instruction"
    private val ExWorColumn_MuscID = "MuscleGroupID"
    private val ExWorColumn_WorID = "WorkoutID"

    /* Question Table */
    private val QueTableName = "Question"
    private val QueColumn_ID = "ID"
    private val QueColumn_Text = "QuestionText"

    /* Answer Table */
    private val AnsTableName = "Answer"
    private val AnsColumn_ID = "ID"
    private val AnsColumn_Text = "AnswerText"

    /* UserQuestRespond Table */
    private val UQRTableName = "UserQuestRespond"
    private val UQRColumn_ID = "ID"
    private val UQRColumn_QuestID = "QuestionID"
    private val UQRCOlumn_AnsID = "AnswerID"
    private val UQRColumn_UserID = "UserID"

    /* WorkoutRecord Table */
    private val WRTableName = "WorkoutRecord"
    private val WRColumn_ID = "ID"
    private val WRColumn_WorID = "WorkoutID"
    private val WRColumn_ExWorID = "ExerciseWorkoutID"
    private val WRColumn_Reps = "Reps"


    /**
     * return  1 : the new user has been add to the database successfully
     * return -1 : Error, adding new user
     * return -2 : can not open database
     * return -3 : login name  already exists
     */

    override fun onCreate(db: SQLiteDatabase?) {
        try{
            val sqlCreateStatement: String = "CREATE TABLE" + UseTableName + " ( " + UseColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + UseColumn_FName + " TEXT NOT NULL, " +
                    UseColumn_LName + " TEXT NOT NULL, " + UseColumn_Email + " TEXT NOT NULL, " +
                    UseColumn_Login + " TEXT NOT NULL, " + UseColumn_Pass + " TEXT NOT NULL, " +
                    UseColumn_Age + " INTEGER NOT NULL, " + UseColumn_Height + " INTEGER NOT NULL, " +
                    UseColumn_Weight + " INTEGER NOT NULL )"
            db?.execSQL(sqlCreateStatement)
        }
        catch (e: SQLiteException) {}
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addUser(user: User): Int{
        val loginNameAlreadyExists = checkLoginName(user)
        if(loginNameAlreadyExists < 0){
            return loginNameAlreadyExists
        }

        val db: SQLiteDatabase
        try{
            db = this.writableDatabase
        }catch (e:SQLiteException){
            return -2
        }

        val cv:ContentValues = ContentValues()

        cv.put(UseColumn_FName, user.fName)
        cv.put(UseColumn_LName, user.lName)
        cv.put(UseColumn_Email,user.email)
        cv.put(UseColumn_Login,user.loginName)
        cv.put(UseColumn_Pass,user.password)
        cv.put(UseColumn_Age, user.age)
        cv.put(UseColumn_Height,user.height)
        cv.put(UseColumn_Weight,user.weight)

        val result = db.insert("$UseTableName",null,cv)
        db.close()
        return if (result.toInt() == -1) result.toInt()
        else 1
    }

    private fun checkLoginName(user: User): Int{

        val db: SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return -2
        }

        val loginName = user.loginName

        val sqlStatement = "SELECT * FROM $UseTableName WHERE $UseColumn_Login = ?"
        val param = arrayOf(loginName)
        val cursor: Cursor = db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            //user exists
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 //login name already exists
        }
        cursor.close()
        db.close()
        return 0 // user is unique
    }

    fun getUser(user: User) : Int {

        val db: SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return -2
        }

        val loginName = user.loginName
        val password = user.password

        val sqlStatement = "SELECT * FROM $UseTableName WHERE $UseColumn_Login = ? AND $UseColumn_Pass = ?"
        val param = arrayOf(loginName,password)
        val cursor: Cursor = db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            //user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -1

    }

    fun retrieveUserID(login: String): Int {

        var id = -1
        val db:SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return -2
        }

        val sqlStatement = "SELECT * FROM $UseTableName WHERE $UseColumn_Login = ?"
        val param = arrayOf(login)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if(cursor.moveToFirst()){
            id = cursor.getInt(0)
            cursor.close()
            db.close()
            return id
        }

        cursor.close()
        db.close()
        return id
    }

    fun createWorkout(workout: Workout): Int {

        val db:SQLiteDatabase
        try{
            db = this.writableDatabase
        }catch (e: SQLiteException){
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(WorColumn_Name, workout.name)
        cv.put(WorColumn_Desc, workout.description)
        cv.put(WorColumn_Musc, workout.muscleGroupID)
        cv.put(WorColumn_Diff, workout.difficulty)

        val result = db.insert("$WorTableName", null, cv)
        db.close()
        return if(result.toInt() == -1){
            result.toInt()
        }
        else 1

    }

    fun getMuscleGroupID(muscleGroup: String): Int {

        val db:SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return -2
        }

        val sqlStatement = "SELECT * FROM $MusTableName WHERE $MusColumn_Name = ?"
        val param = arrayOf(muscleGroup)

        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if(cursor.moveToFirst()){
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }
        cursor.close()
        db.close()
        return -1

    }

    fun getMuscleGroupName(ID: Int): String {

        val db:SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e:SQLiteException){
            return "Error connecting to database"
        }

        val sqlStatement = "SELECT * FROM $MusTableName WHERE $MusColumn_ID = ?"
        val idString = ID.toString()
        val param = arrayOf(idString)

        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if(cursor.moveToFirst()){
            val name = cursor.getString(1)
            cursor.close()
            db.close()
            return name
        }
        cursor.close()
        db.close()
        return "Error: muscle group not found"

    }

    fun getAllExercises(): ArrayList<Exercise>{

        val exerciseList = ArrayList<Exercise>()
        val db: SQLiteDatabase = this.readableDatabase

        val sqlStatement = "SELECT * FROM $ExTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val instruction = cursor.getString(2)
                val muscID = cursor.getInt(3)

                val exercise = Exercise(id, name, instruction, muscID)

                exerciseList.add(exercise)
            }while (cursor.moveToNext())

            cursor.close()
            db.close()
        }
        return exerciseList

    }

    fun getAllExerciseWorkouts(workoutID: Int): ArrayList<ExerciseWorkout>{

        val exerciseList = ArrayList<ExerciseWorkout>()
        val db:SQLiteDatabase = this.readableDatabase

        val sqlStatement = "SELECT * FROM $ExWorTableName WHERE $ExWorColumn_WorID = ?"
        val param = arrayOf(workoutID.toString())

        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val instr = cursor.getString(2)
                val muscID = cursor.getInt(3)
                val worID = cursor.getInt(4)

                val exercise = ExerciseWorkout(id,name,instr,muscID,worID)

                exerciseList.add(exercise)
            }while (cursor.moveToNext())

            cursor.close()
            db.close()
        }
        return exerciseList

    }

    fun getExerciseDetails(param: String): ArrayList<String>{
        val nameList = ArrayList<String>()
        val instrList = ArrayList<String>()
        val muscGroupList = ArrayList<String>()
        val emptyList = ArrayList<String>()
        val db:SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return emptyList
        }
        val sqlStatement = "SELECT * FROM $ExTableName"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if(cursor.moveToFirst()){
            do{
                val name = cursor.getString(1)
                val instr = cursor.getString(2)
                val muscID = cursor.getInt(3)
                val musc = getMuscleGroupName(muscID)

                nameList.add(name)
                instrList.add(instr)
                muscGroupList.add(musc)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        if(param == "name"){
            return nameList
        }
        if(param == "instruction"){
            return instrList
        }
        if(param == "muscleGroup"){
            return muscGroupList
        }
        else{
            //exception
            return emptyList
        }

    }

    fun getExerciseName(id: Int): String {

        val db:SQLiteDatabase
        try {
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return "Error connecting to database"
        }

        val sqlStatement = "SELECT * FROM $ExTableName WHERE $ExColumn_ID = ?"
        val param = arrayOf(id.toString())

        val cursor: Cursor = db.rawQuery(sqlStatement, param)

        if(cursor.moveToFirst()){
            val name = cursor.getString(1)
            cursor.close()
            db.close()
            return name
        }
        cursor.close()
        db.close()
        return "Error: exercise not found :("

    }

    fun addExerciseToWorkout(exerciseID: Int, workoutID: Int): Int {

        val exercise = getExercise(exerciseID)

        val db:SQLiteDatabase
        try{
            db = this.writableDatabase
        }catch (e:SQLiteException){
            return -2
        }


        val cv:ContentValues = ContentValues()
        cv.put(ExWorColumn_Name, exercise.name)
        cv.put(ExWorColumn_Instr, exercise.instruction)
        cv.put(ExWorColumn_MuscID, exercise.muscleGroupID)
        cv.put(ExWorColumn_WorID, workoutID)

        val result = db.insert("$ExWorTableName", null, cv)
        db.close()
        return if (result.toInt() == -1) result.toInt()
        else 1

    }

    fun getWorkoutID(name: String): Int {

        val db:SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e:SQLiteException){
            return -2
        }

        val sqlStatement = "SELECT * FROM $WorTableName WHERE $WorColumn_Name = ?"
        val param = arrayOf(name)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if(cursor.moveToFirst()){
            val id = cursor.getInt(0)
            cursor.close()
            db.close()
            return id
        }
        cursor.close()
        db.close()
        return -1

    }

    fun getExercise(id: Int): Exercise {

        val emptyExercise = Exercise(-1,"","",-1)
        val sqlExercise = Exercise(-1,"SQL","",-1)
        val db: SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return sqlExercise
        }

        val sqlStatement = "SELECT * FROM $ExTableName WHERE $ExColumn_ID = ?"
        val param = arrayOf(id.toString())
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if(cursor.moveToFirst()){
            val name = cursor.getString(1)
            val instruction = cursor.getString(2)
            val muscID = cursor.getInt(3)
            val exercise = Exercise(id,name,instruction,muscID)
            cursor.close()
            db.close()
            return exercise
        }
        else{
            cursor.close()
            db.close()
            return emptyExercise
        }

    }

    fun retrieveAnswerID(text: String): Int {

        var id = -1
        val db:SQLiteDatabase
        try{
            db = this.readableDatabase
        }catch (e: SQLiteException){
            return -2
        }

        val sqlStatement = "SELECT * FROM $AnsTableName WHERE $AnsColumn_Text = ?"
        val param = arrayOf(text)
        val cursor: Cursor = db.rawQuery(sqlStatement, param)
        if(cursor.moveToFirst()){
            id = cursor.getInt(0)
            cursor.close()
            db.close()
            return id
        }

        cursor.close()
        db.close()
        return id

    }

    fun getQuestions(): ArrayList<Question>{

        val questionList = ArrayList<Question>()

        val db: SQLiteDatabase = this.readableDatabase

        val sqlStatement = "SELECT * FROM $QueTableName"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()){
            do{
                val id: Int = cursor.getInt(0)
                val text: String = cursor.getString(1)

                val question = Question(id,text)
                questionList.add(question)
            }while (cursor.moveToNext())

            db.close()
            cursor.close()
        }
        db.close()
        cursor.close()

        return questionList
    }

    fun addResponse(response: UserQuestRespond): Int {

        val db: SQLiteDatabase
        try{
            db = this.writableDatabase
        }catch (e:SQLiteException){
            return -2
        }
        val cv: ContentValues = ContentValues()

        cv.put(UQRColumn_QuestID, response.questionID)
        cv.put(UQRCOlumn_AnsID, response.answerID)
        cv.put(UQRColumn_UserID, response.userID)

        val result = db.insert("$UQRTableName",null,cv)
        db.close()

        return if (result.toInt() == -1) result.toInt()
        else 1

    }

    fun getAllWorkouts(): ArrayList<Workout>{

        val workoutList = ArrayList<Workout>()
        val db:SQLiteDatabase = this.readableDatabase

        val sqlStatement = "SELECT * FROM $WorTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val desc = cursor.getString(2)
                val muscID = cursor.getInt(3)
                val diff = cursor.getString(4)

                val workout = Workout(id,name,desc,muscID,diff)

                workoutList.add(workout)
            }while (cursor.moveToNext())
            cursor.close()
            db.close()
        }
        return workoutList

    }

    fun getWorkoutDetails(param: String): ArrayList<String> {

        val nameList = ArrayList<String>()
        val descList = ArrayList<String>()
        val emptyList = ArrayList<String>()

        val db:SQLiteDatabase

        try {
            db = this.readableDatabase
        }catch (e:SQLiteException){
            return emptyList
        }

        val sqlStatement = "SELECT * FROM $WorTableName"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if(cursor.moveToFirst()){
            do{
                val name = cursor.getString(1)
                val desc = cursor.getString(2)

                nameList.add(name)
                descList.add(desc)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        if(param == "name"){
            return nameList
        }
        else if(param == "desc"){
            return descList
        }
        else{
            return emptyList
        }

    }

    fun addRecord(record: WorkoutRecord): Int {

        val db:SQLiteDatabase
        try{
            db = this.writableDatabase
        }catch (e:SQLiteException){
            return -2
        }

        val cv:ContentValues = ContentValues()

        cv.put(WRColumn_WorID, record.workoutID)
        cv.put(WRColumn_ExWorID, record.exerciseWorkoutID)
        cv.put(WRColumn_Reps, record.reps)

        val result = db.insert("$WRTableName",null,cv)
        db.close()

        return if (result.toInt() == -1) result.toInt()
        else 1

    }

}