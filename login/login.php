<?php
// Check if email and password are set
if(isset($_POST['email']) && isset($_POST['password'])){
    // Include the necessary files
    require_once "conn.php";
    require_once "validate.php";
    // Call validate, pass form data as parameter and store the returned value
    $email = validate($_POST['email']);
    $password = validate($_POST['password']);
    // Create the SQL query string
    $sql = "select * from loginfood where email='$email' and password='" . md5($password) . "' and position = '0'";
    // Execute the query
    $result = $conn->query($sql);
    // If number of rows returned is greater than 0 (that is, if the record is found), we'll print "success", otherwise "failure"
    if($result->num_rows > 0){
        echo "success";
    }else{
        $sql1 = "select * from loginfood where email='$email' and password='" . md5($password) . "' and position = '1'";

        $result1 = $conn->query($sql1);

        if ($result1->num_rows > 0) {
            echo "user";
        }else{
            echo "failure";
        }
    }
}
?>