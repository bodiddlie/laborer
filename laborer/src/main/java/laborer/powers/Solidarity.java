package laborer.powers;

import static laborer.LaborerMod.makePowerPath;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import laborer.LaborerMod;
import laborer.stances.WorkStoppageStance;
import laborer.util.TextureLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solidarity extends AbstractPower implements CloneablePowerInterface {

  public static final Logger logger = LogManager.getLogger(Solidarity.class.getName());
  public boolean isProducing = true;
  public static final String POWER_ID = LaborerMod.makeID("Solidarity");
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack
      .getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

  private static final Texture tex84 = TextureLoader
      .getTexture(makePowerPath("placeholder_power84.png"));
  private static final Texture tex32 = TextureLoader
      .getTexture(makePowerPath("placeholder_power32.png"));

  public Solidarity(final AbstractCreature owner, final int amount) {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;

    this.type = PowerType.BUFF;

    this.region128 = new AtlasRegion(tex84, 0, 0, 84, 84);
    this.region48 = new AtlasRegion(tex32, 0, 0, 32, 32);

    this.updateDescription();
  }

  @Override
  public void onUseCard(final AbstractCard card, final UseCardAction action) {
  }

  @Override
  public void atEndOfTurn(final boolean isPlayer) {
    if (isPlayer) {
      AbstractPlayer p = (AbstractPlayer) this.owner;
      if (p.stance.ID.equals(WorkStoppageStance.STANCE_ID)) {
        this.flash();
        this.addToBot(new SwordBoomerangAction(
            AbstractDungeon.getMonsters().
                getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng),
            new DamageInfo(this.owner, this.amount, DamageType.THORNS), 1
        ));
      }
    }
  }

  @Override
  public AbstractPower makeCopy() {
    return new Solidarity(owner, amount);
  }

  @Override
  public void updateDescription() {
    if (amount == 1) {
      this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    } else if (amount > 1) {
      this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
    }
  }
}
