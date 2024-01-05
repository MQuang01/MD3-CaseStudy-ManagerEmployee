DELIMITER |

DROP EVENT IF EXISTS addRecordTimeKeeping;
CREATE EVENT  addRecordTimeKeeping
ON SCHEDULE every 1 day
ON COMPLETION PRESERVE ENABLE
DO
begin
  call ProcessAllMembers('00:30:00', '7:00:00');
  end |
DELIMITER ;


SET GLOBAL event_scheduler = OFF;
SET GLOBAL event_scheduler = ON;
