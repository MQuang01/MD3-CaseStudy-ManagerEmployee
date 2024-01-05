delimiter |

CREATE DEFINER=`root`@`localhost` PROCEDURE `ProcessAllMembers`(IN from_time TIME, IN to_time TIME)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE memberId INT;
    
    -- Khai báo một con trỏ (cursor) để duyệt qua các thành viên
    DECLARE memberCursor CURSOR FOR 
        SELECT id FROM members;
    
    -- Khai báo một bộ dữ liệu với các thành viên
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    IF CURRENT_TIME() BETWEEN from_time AND to_time THEN
		OPEN memberCursor;
		memberLoop: LOOP
			FETCH memberCursor INTO memberId;
			
			IF done THEN
				LEAVE memberLoop;
			END IF;

			-- Thực hiện hành động tương ứng với từng thành viên
			INSERT INTO time_keepings (memberId)
			VALUES (memberId);
		END LOOP memberLoop;
		
		CLOSE memberCursor;
    END IF;
END |
delimiter ;