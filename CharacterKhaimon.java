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
        
        public CharacterKhaimon(int HP, int MP){
            this.HP = HP;
            this.maxHP = HP;

            this.MP = MP;
            this.maxMP = MP;        
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

        
        public int skillOne(Character[] party) {
            Random ran = new Random();
            if (MP >= 100 && party.length > 1) {
                MP -= 100;

                Character controlled = party[ran.nextInt(party.length)];
                Character targetHero;
                do{
                    targetHero = party[ran.nextInt(party.length)];
                }while(controlled == targetHero);
                int skillOneDamage = controlled.skillOne();
                
                targetHero.setHP(targetHero.getHP() - skillOneDamage);
                System.out.println("Corrupted Khaimon used Mind control on "+ controlled.displayName() + " to used a skill on " + targetHero.displayName() + " dealing " + skillOneDamage + " damage!");
                return skillOneDamage;
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
                    damageDealt = ogre.skillOne();
                    skillUsed = "Ogre's Tree trunk";
                }
                else{
                    damageDealt = goblin.skillOne();
                    skillUsed = "Goblin's Club smush";
                }
                
                
                System.out.println("Corrupted Khaimon used " + skillUsed + "! Dealt " + damageDealt + " damage!");
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
                int skillThreeDamage = ran.nextInt(101) + 100;
                MP-=150;
                System.out.println("Corrupted Khaimon used Meteor shower! Dealt "+skillThreeDamage +" damage!");
                return skillThreeDamage;
            } else {
                System.out.println("Not enough MP.");
                return -1;
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
        
    }
