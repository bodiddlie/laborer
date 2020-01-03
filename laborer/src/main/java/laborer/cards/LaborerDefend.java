package laborer.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import laborer.LaborerMod;
import laborer.characters.Laborer;

import static laborer.LaborerMod.makeCardPath;

public class LaborerDefend extends CustomCard {
  public static final String ID = LaborerMod.makeID(LaborerDefend.class.getSimpleName());
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String IMG = makeCardPath("Skill.png");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;

  private static final CardRarity RARITY = CardRarity.BASIC;
  private static final CardTarget TARGET = CardTarget.SELF;
  private static final CardType TYPE = CardType.SKILL;
  private static final CardColor COLOR = Laborer.Enums.COLOR_GRAY;

  private static final int COST = 1;
  private static final int BLOCK = 5;
  private static final int UPGRADE_PLUS_BLOCK = 3;

  public LaborerDefend() {
    super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    this.baseBlock = BLOCK;

    this.tags.add(BaseModCardTags.BASIC_DEFEND);
  }

  @Override
  public void use(AbstractPlayer player, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, block));
  }

  @Override
  public void upgrade() {
    if (!upgraded) {
      upgradeName();
      upgradeBlock(UPGRADE_PLUS_BLOCK);
      initializeDescription();
    }
  }
}
