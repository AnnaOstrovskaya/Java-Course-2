public class HW1 {

    public static void main(String[] args) {

        Team teamMissionImpossible = new Team("Mission Impossible",
                new Agent[]{
                        new Agent("Ethan", "Hunt", "Agent", 20),
                        new Agent("Luther", "Stickell", "Tech", 10),
                        new Agent("Benji", "Dunn", "Tech/FieldAgent", 5),
                        new Agent("Ilsa", "Faust", "FieldAgent", 15),
                }
        );

        Course course = new Course(new Integer[]{15, 5, 10, 8});
        String results = course.Traverse(teamMissionImpossible);
        System.out.println(results);


        System.out.println();
    }
}

class Agent {
    public String firstname;
    public String surname;
    private String position;
    public Integer jumpHeight;

    Agent(String firstname, String surname, String position, Integer jumpHeight) {
        this.firstname = firstname;
        this.surname = surname;
        this.position = position;
        this.jumpHeight = jumpHeight;
    }

    public String toString() {
        return firstname + " " + surname + "," + position + "," + jumpHeight;
    }

}

class Team {
    public String name;
    public String surname;
    public Agent[] agents;

    Team(String name, Agent[] particpants) {
        this.name = name;
        this.surname = surname;
        this.agents = particpants;
    }

    public String toString() {
        String teamInfo = "Team details: \n";
        teamInfo += "Name: " + this.name + this.surname + " \n";
        for (Agent agent : this.agents) {
            teamInfo += "Participant: " + agent.toString() + " \n";
        }
        return teamInfo;
    }

}

class Course {
    Integer[] obstacleHeights;

    Course(Integer[] obstacleHeights) {
        this.obstacleHeights = obstacleHeights;
    }

    public String Traverse(Team team){
        String results = "";

        for (Agent agent : team.agents) {
            for (Integer obstacleHeigh : this.obstacleHeights) {
                if (obstacleHeigh > agent.jumpHeight) {
                    results += "Participant " + agent.firstname + " " + agent.surname +" failed to jump " + obstacleHeigh.toString() + '\n';
                } else {
                    results += "Participant " + agent.firstname + " " + agent.surname + " succeesed to jump " + obstacleHeigh.toString() + '\n';
                }
            }
        }

        return results;
    }


}