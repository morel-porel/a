    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package game;

    import java.util.Random;

    /**
     *
     * @author User
     */


    public class CharacterKhaimon extends Character{
        private int HP;
        private int maxHP;
        private int MP;
        private int maxMP;
        private int buffTurnsRemaining;
        private boolean buffActive;
        private int buffDamage;
        private int buffPercentage;

        //range
    private String range1;
    private String range2;
    private String range3;
        
        public CharacterKhaimon(){
            HP = 500;
            maxHP = HP;
    
            MP = 9999; //bc dali kayu ma drain
            maxMP = MP;        
        }
        @Override
        public String displayName(){
            return "Corrupted Khaimon";
        }
        @Override
        public void setHP(int HP){
            this.HP = Math.min(HP, maxHP);
        }
        @Override
        public int getHP(){
            return HP;
        }
        @Override
        public int getMaxHP() {
            return maxHP;
        }
        @Override
        public void setMP(int MP){
            this.MP = Math.min(MP, maxMP);
        }
        @Override
        public int getMP(){
            return MP;
        }
        @Override
        public int getMaxMP() {
            return maxMP;
        }

        @Override
        public int getBuffTurnsRemaining(){
            return buffTurnsRemaining;
        }

        @Override
    public int applyBuff(int baseDamage){
        if(buffActive){
            buffDamage = baseDamage * buffPercentage / 100;
            int modifiedDamage = baseDamage + buffDamage;
            return modifiedDamage;//increase damage by 20%
        }
        return baseDamage;// No buff applied
    }
    @Override
    public void setBuffPercentage(int buffPercentage){
        this.buffPercentage = buffPercentage;
    }
    @Override    
    public void setBuffActive(boolean buffActive){
        this.buffActive = buffActive;
    }
    @Override    
    public void setBuffTurnsRemaining(int buffTurnsRemaining){
        this.buffTurnsRemaining = buffTurnsRemaining;
    }
    @Override    
    public boolean isBuffActive(){
        return buffActive;
    }

    public boolean isOnlyOneAlive(Character[] party) { //counts for last member alive
        int aliveCount = 0;
        for (Character character : party) {
            if (character.isAlive()) {
                aliveCount++;
            }
        }
        return aliveCount == 1;
    }
        
    public int skillOne(Character[] party, Character controlled) {
        Random ran = new Random();
        if (MP >= 100 && party.length > 1) {
            MP -= 100;
            System.out.println("Corrupted Khaimon used mind control!");
            Character target;
            if (isOnlyOneAlive(party)) { // Only one character left
                System.out.println("Corrupted Khaimon twists the last hero's mind against their own.");
                int mentalDamage = applyBuff(ran.nextInt(50-10+1) + 10);
                if(isBuffActive()){
                    System.out.println(controlled.displayName() + " succumbs, dealing " + (mentalDamage-buffDamage) +" + "+buffDamage+" damage under Khaimon's control...");
                } else {
                    System.out.println(controlled.displayName() + " succumbs, dealing " + mentalDamage + " damage under Khaimon's control...");
                }
                return mentalDamage;
            } else {
                do{
                    target = party[ran.nextInt(party.length)];
                }while(controlled == target || !target.isAlive());
            }
            int baseMP = controlled.getMP();
            
            int skillDamage=0;
            int skillChoice = ran.nextInt(3) + 1;
            switch (skillChoice) {
                case 1:
                    controlled.setMP(baseMP + controlled.skillOneMP());                    
                    skillDamage = applyBuff(controlled.skillOne());
                    int displayDamage = skillDamage;
                    if(skillDamage<0) displayDamage = Math.abs(skillDamage + 2);
                    if(isBuffActive()){
                            System.out.println(controlled.displayName() + "'s attack hit " + target.displayName() + " dealing " + (displayDamage-buffDamage) +" + "+buffDamage+" damage!");
                    } else {
                            System.out.println(controlled.displayName() + "'s attack hit " + target.displayName() + " dealing " + displayDamage + " damage!");
                    }
                    break;
                case 2:
                    if(controlled instanceof CharacterElara){
                        int heal=((CharacterElara)controlled).skillTwo(displayName());
                        setHP(getHP()+heal);
                        skillDamage=-99;
                    } else {
                        controlled.setMP(baseMP + controlled.skillTwoMP());
                        skillDamage = applyBuff(controlled.skillTwo());
                        if(isBuffActive()){
                            System.out.println(controlled.displayName() + "'s attack hit " + target.displayName() + " dealing " + (skillDamage-buffDamage) +" + "+buffDamage+" damage!");
                        } else {
                            System.out.println(controlled.displayName() + "'s attack hit " + target.displayName() + " dealing " + skillDamage + " damage!");
                        }
                    }
                    
                    break;
                case 3:
                    if(controlled instanceof CharacterElara){
                        int buffPercent = ((CharacterElara)controlled).skillThree(displayName());
                        setBuffPercentage(buffPercent);
                        setBuffActive(true);
                        setBuffTurnsRemaining(2);
                    } else {
                        controlled.setMP(baseMP + controlled.skillThreeMP());
                        skillDamage = applyBuff(controlled.skillThree());
                        if(isBuffActive()){
                            System.out.println(controlled.displayName() + "'s attack hit " + target.displayName() + " dealing " + (skillDamage-buffDamage) +" + "+buffDamage+" damage!");
                        } else {
                            System.out.println(controlled.displayName() + "'s attack hit " + target.displayName() + " dealing " + skillDamage + " damage!");
                        }
                    }
                    break;
                default:
                    skillDamage = 0; // Fallback, though this shouldn't happen
            }  
            
            controlled.setMP(baseMP); //reset MP
            
            return skillDamage;
            }else{
                System.out.println("Not enough MP for mind control.");
                return 0;
            }
    }
        //CHANGED skillTwo from Summon to Using abiliies of Ogre and Goblin
        @Override
        public int skillTwo(){
            Random ran = new Random();
            if(MP>=200){
                MP-=200;
                MonsterOgre ogre = new MonsterOgre(100);
                MonsterGoblin goblin = new MonsterGoblin(100);
                
                int skillChoice = ran.nextInt(2);
                
                int damageDealt;
                String skillUsed;
                
                if(skillChoice == 0){
                    System.out.println("Corrupted Khaimon summoned an ogre!");
                    damageDealt = applyBuff(ogre.skillOne());
                    if(isBuffActive()){
                        System.out.println("Attacks are amplified! "+(damageDealt-buffDamage) +" + "+buffDamage);
                    }
                } else{
                    System.out.println("Corrupted Khaimon summoned a goblin!");
                    damageDealt = applyBuff(goblin.skillOne());
                    if(isBuffActive()){
                        System.out.println("Attacks are amplified! "+(damageDealt-buffDamage) +" + "+buffDamage);
                    }
                }
                return damageDealt; 
            }
            else{
            System.out.println("Not enough MP.");
            return 0;
            }
        }
    
        @Override
        public int skillThree(){
            Random ran = new Random();
            if(MP>=150){
                int skillThreeDamage = applyBuff(ran.nextInt(101) + 100);
                MP-=150;
                if(isBuffActive()){
                    System.out.println("Corrupted Khaimon used Meteor shower! Dealt "+(skillThreeDamage-buffDamage) +" + "+buffDamage+" damage!");
                } else {
                    System.out.println("Corrupted Khaimon used Meteor shower! Dealt "+skillThreeDamage +" damage!");
                }
                return skillThreeDamage;
            } else {
                System.out.println("Not enough MP.");
                return 0;
            }
        }
        
        @Override
        public String getSkillOne() {
            return "Mind Control (Can control any member from hero party for one turn)";
        }

        @Override
        public String getSkillTwo() {
            return "Stole attack ability from Hero Party";
        }

        @Override
        public String getSkillThree() {
            return "Meteor Shower";
        }    

        @Override
        public int skillOneMP() {
            return 100;
        }

        @Override
        public int skillTwoMP() {
            return 200;
        }

        @Override
        public int skillThreeMP() {
            return 150;
        }
        @Override
        public int skillOne(){
            return 0;
        }
        @Override
            public void lvlUpCharacter() {
            System.out.println("Khaimon is at max level!");
        }

    // wala pa na update iyang damage range
    // range1
    @Override
    public void setDMG1(String range1){
        this.range1 = range1;
    }
    @Override 
    public String getDMG1(){
        return "DMG: 5-15";
    }
    // range 2
    @Override
    public void setDMG2(String range2){
        this.range2 = range2;
    }
    @Override 
    public String getDMG2(){
        return "DMG: 10-25";
    }
    // range 3
    @Override
    public void setDMG3(String range3){
        this.range3 = range3;
    }
    @Override 
    public String getDMG3(){
        return "DMG: 75-100";
    }
        
    }
