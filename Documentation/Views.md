# Views

This file contains two views that provide useful information for analysis and tracking purposes.

## Members and total spent

This view displays each member and the total amount they have spent on rentals since joining, useful for identifying the customers who are responsible for the businesses revenue. The view can be created using the following SQL:

	CREATE VIEW Cust_Orders_Value AS
	SELECT M.User_Id, Fname, Lname, Start_Date, SUM(Value) "Total_Orders_Value"
	FROM MEMBER AS M, CUST_ORDER AS O
	WHERE M.User_Id = O.User_Id
	GROUP BY M.User_Id;

The query implements the following relational algebra (screenshot in Views Relational Algebra subdirectory for convenience):

## Drones, total orders, and manufacturer

This view displays each drone, the total number of orders it has delivered, its description, and its manufacturer, useful for tracking drone use and predicting when drones will need to be ordered. The view can be created using the following SQL:

	CREATE VIEW Drone_Deliveries AS
	SELECT D.Model_Num, D.Serial_Num, D.Desc, S.Name, SUM(I.Num_Items) AS Orders_Delivered
	FROM DRONE AS D
		JOIN FOR_DRONE AS F ON D.Fleet_Id = F.Fleet_Id
		JOIN INV_ORDER AS I ON F.Order_Num = I.Order_Num
		JOIN SUPPLIER AS S ON D.Supp_Id = S.Supp_Id
	GROUP BY D.Model_Num, D.Serial_Num, S.Name, D.Desc;

