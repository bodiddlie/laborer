package laborer.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import laborer.LaborerMod;
import laborer.characters.Laborer;

import static laborer.LaborerMod.makeCardPath;

public class LaborerStrike extends CustomCard {
  public static final String ID = LaborerMod.makeID(LaborerStrike.class.getSimpleName());
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String IMG = makeCardPath("Attack.png");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;

  // STATS
  private static final CardRarity RARITY = CardRarity.BASIC;
  private static final CardTarget TARGET = CardTarget.ENEMY;
  private static final CardType TYPE = CardType.ATTACK;
  public static final CardColor COLOR = Laborer.Enums.COLOR_GRAY;

  private static final int COST = 1;
  private static final int DAMAGE = 6;
  private static final int UPGRADE_PLUS_DMG = 3;

  public LaborerStrike() {
    super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    this.baseDamage = DAMAGE;

    this.tags.add(BaseModCardTags.BASIC_STRIKE);
    this.tags.add(CardTags.STRIKE);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster monster) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(monster,
            new DamageInfo(player, damage, damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)
    );
  }

  @Override
  public void upgrade() {
    if (!upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
      initializeDescription();
    }
  }
}
