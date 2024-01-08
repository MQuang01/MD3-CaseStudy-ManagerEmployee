package com.example.csmd3checkin.dao.Impl;

import com.example.csmd3checkin.context.DBConnect;
import com.example.csmd3checkin.dao.IMemberDAO;
import com.example.csmd3checkin.model.Account;
import com.example.csmd3checkin.model.Member;
import com.example.csmd3checkin.model.Project;
import com.example.csmd3checkin.model.Team;
import com.example.csmd3checkin.model.enumration.ERole;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DBConnect implements IMemberDAO {
    private static final String SELECT_MEMBER = "select * from members " +
                                                    "inner join accounts on accounts.id = members.accounts_id " +
                                                        "where ( accounts.id = ? );";
    private static final String SELECT_INFO_MEMBER_BY_ID="select members.id,accounts.`role`, members.`name`,members.phone,members.dob,members.email,teams.id as teamId,teams.`name` as teamName,  " +
            "GROUP_CONCAT(projects.`name` SEPARATOR ', ') as projectsName from members " +
            "join teams join accounts join projects " +
            "on members.teamId=teams.id  and  projects.teamId=members.teamId  and accounts.id = members.accounts_id " +
            "where ( members.id = ? ) " +
            "group by accounts.`role`, members.`name`,members.phone,members.dob,members.email,teams.id,teams.`name`;";
    private static final String SELECT_MEMBERS_TEAMS="select * from members inner join accounts on accounts.id = members.accounts_id " +
            "inner join teams on teams.id = members.teamId " +
            "group by members.id, members.`name`;";

    private static final String SELECT_TEAMMATE = "select * from members where (teamId = ?);";

    private static final String SELECT_ALL_MEMBERS = "select * from members";
    private static final String INSERT_MEMBERS_SQL = "INSERT INTO members (name,phone,dob,email,teamId,accounts_Id) VALUES (?,?,?,?,?,?);";

    private static final String DELETE_MEMBERS="delete from members where id = ?;";
    private static final String UPDATE_MEMBER_SQL = "update members set name = ?,phone=?,dob=?,email= ?, country =?,teamId=?,accounts_Id=? where id = ?;";
    private static final String SELECT_ALL_MEMBER_TYPE = "select * from members inner join accounts on members.accounts_Id = accounts.id where ( role = ? )";
    public static final String SELECT_TEAM_BY_ID = "select * from teams where (id = ?)";
    private static final String SELECT_ACCOUNT_BY_ID = "select * from accounts where (id = ?)";
    private static final String SELECT_PROJECT_OF_TEAM = "select projects.`name` from projects " +
            "inner join teams on teams.id = projects.teamId " +
            "where teams.id = ?";


    public MemberDAO() {
    }

    @Override
    public Member selectInfoMemberById(int id, Account account) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_INFO_MEMBER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                Team team= selectTeamById(rs.getInt("teamId"));
                List<Project> projects = selectProjectOfTeam(rs.getInt("teamId"));

                int idMember = rs.getInt("id");
                String nameMember = rs.getString("name");
                String phoneMember = rs.getString("phone");
                LocalDate date = rs.getDate("dob").toLocalDate();
                String email = rs.getString("email");
                int teamId = rs.getInt("teamId");

                return new Member(idMember, nameMember, phoneMember, date, email, teamId,team, account, projects);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Member selectMemberById(int id, Account account) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_MEMBER)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                int idMember = rs.getInt("id");
                String nameMember = rs.getString("name");
                String phoneMember = rs.getString("phone");
                LocalDate date = rs.getDate("dob").toLocalDate();
                String email = rs.getString("email");
                int teamId = rs.getInt("teamId");

                return new Member(idMember, nameMember, phoneMember, date, email, teamId, account);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Member> selectMemberTeam() {
        List<Member> members = new ArrayList<>();
        try {
            Connection connection= getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_MEMBERS_TEAMS);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();



            while (rs.next()){
                Team team = selectTeamById(rs.getInt("teamId"));
                Account account= selectAccountById(rs.getInt("accounts_id"));

                members.add(new Member(
                        rs.getString("members.name"),
                        rs.getString("members.phone"),
                        rs.getDate("members.dob").toLocalDate(),
                        rs.getString("members.email"),
                        team,
                        account
                ));
            }

//            List<Project> projects = selectProjectOfTeam();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return members;
    }

    private List<Project> selectProjectOfTeam(int teamId) {
        List<Project> projects = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_PROJECT_OF_TEAM)) {
            preparedStatement.setInt(1, teamId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                projects.add(new Project(rs.getString("name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    private Account selectAccountById(int accountsId) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ACCOUNT_BY_ID)) {
            preparedStatement.setInt(1, accountsId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Account(ERole.findByName(rs.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Member> selectAllMember() {
        List<Member> member=new ArrayList<>();
        try {
            Connection connection= getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_MEMBERS);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                member.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getString("email"),
                        rs.getInt("teamId"),
                        rs.getInt("accounts_Id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return member;
    }
    @Override
    public void insertMember(Member member) {
        System.out.println(INSERT_MEMBERS_SQL);
        try (Connection connection=getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(INSERT_MEMBERS_SQL)){

            preparedStatement.setString(1,member.getFullName());
            preparedStatement.setString(2,member.getPhoneNum());

            Date parsedLocalDate =Date.valueOf(member.getDoB());
            preparedStatement.setDate(3,parsedLocalDate);

            preparedStatement.setString(4,member.getEmail());
            preparedStatement.setInt(5,member.getTeamId());
            preparedStatement.setInt(6,member.getAccountId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteMember(int id) {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_MEMBERS);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean updateMember(Member member) {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_MEMBER_SQL);) {
            statement.setString(1,member.getFullName());
            statement.setString(2,member.getPhoneNum());

            Date parsedLocalDate =Date.valueOf(member.getDoB());
            statement.setDate(3,parsedLocalDate);

            statement.setString(3,member.getEmail());
            statement.setInt(4,member.getTeamId());
            statement.setInt(5,member.getAccountId());

            rowUpdated = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
    @Override
    public List<Member> selectAllMemberType(ERole role) {
        List<Member> memberList = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_MEMBER_TYPE)) {
            preparedStatement.setString(1, String.valueOf(role));

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                LocalDate doB = rs.getDate("dob").toLocalDate();
                String mail = rs.getString("email");

                int teamId = rs.getInt("teamId");
                MemberDAO memberDAO = new MemberDAO();
                Team team = memberDAO.selectTeamById(teamId);

                memberList.add(new Member(id , name, phone, doB, mail, team));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }

    @Override
    public List<Member> selectTeamMates(Member member) {
        List<Member> memberList = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_TEAMMATE)){
            preparedStatement.setInt(1, member.getTeamId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt( "id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int teamId = rs.getInt("teamId");
                Team team = selectTeamById(teamId);

                memberList.add(new Member(id, name, email, team));            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }

    public Team selectTeamById(int id){
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_TEAM_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Team(rs.getInt("id"), rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
