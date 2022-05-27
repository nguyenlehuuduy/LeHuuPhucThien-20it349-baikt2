<?php 

 require_once "conn.php";
    require_once "validate.php";
    // Call validate, pass form data as parameter and store the returned value
    // Create the SQL query string
    $sql = "select * from loginfood ";
    // Execute the query
    $result = $conn->query($sql);
    // If number of rows returned is greater than 0 (that is, if the record is found), we'll print "success", otherwise "failure"
    if($result->num_rows > 0){
        while ($row = mysqli_fetch_assoc($result)) {
           echo $row['email'];
        }
    }else{

    }
 ?>