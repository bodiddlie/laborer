package laborer.stances;

import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import laborer.LaborerMod;

public class WorkStoppageStance extends AbstractStance {
  public static final String STANCE_ID = LaborerMod.makeID("Stoppage");
  private static final StanceStrings stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);

  public WorkStoppageStance() {
    this.ID = STANCE_ID;
    this.name = stanceStrings.NAME;
    //this.description = stanceStrings.DESCRIPTION;
    this.updateDescription();
  }

  @Override
  public float atDamageGive(float damage, DamageType type) {
    return damage;
  }

  @Override
  public float atDamageReceive(float damage, DamageType type) {
    return type == DamageType.NORMAL ? damage * 0.5f : damage;
  }

  @Override
  public void updateDescription() {
    this.description = stanceStrings.DESCRIPTION[0];
  }
}
