from twitchio.ext import commands
from dotenv import load_dotenv
from os import environ
import requests


class Bot(commands.Bot):

    def __init__(self, token, prefix, initChannels):
        super().__init__(token=token, prefix=prefix, initial_channels=initChannels)

    async def event_ready(self):
        print(f'Logged in as | {self.nick}')
        print(f'User id is | {self.user_id}')

    async def event_message(self, message):
        # Messages with echo set to True are messages sent by the bot...
        # For now we just want to ignore them...
        if message.echo:
            return

        # Print the contents of our message to console...
        print(f'Message: {message.content} from {message.author.name}')

        # Since we have commands and are overriding the default `event_message`
        # We must let the bot know we want to handle and invoke our commands...
        await self.handle_commands(message)
        

    # twitch command to add a vote to a poll
    @commands.command()
    async def vote(self, ctx: commands.Context, *, poll="0"):
        url = "http:localhost:8080/api/poll/vote?poll="+poll
        print(f'Request to {url}')
        response = requests.get(url)
        if response.status_code == 200:
            print(f'Vote added to poll {poll} | Status code: {response.status_code}')
            await ctx.send(f'Thanks for voting!')
        else:
            print(f'Vote not added to poll {poll} | Status code: {response.status_code}')
            await ctx.send(f'Vote not added!')
        print(f'{url}')

    # twitch command to show the current poll
    @commands.command()
    async def poll(self, ctx: commands.Context, poll="0"):
        url = "http:localhost:8080/api/poll/getPollString?poll="+poll
        print(f'Request to {url}')
        response = requests.get(url)
        if response.status_code == 200:
            await ctx.send(f'Current poll: {response.text}')
            print(f'Poll recovered with value {response.text} | Status code: {response.status_code}')
        else:
            await ctx.send(f'No current poll!')
            print(f'Poll not found | Status code: {response.status_code}')
        print(f'{url}')

load_dotenv()
token = environ['ACCESS_TOKEN']
botPrefix = environ['BOT_PREFIX']
initChannels = environ['INITIAL_CHANNELS'].split()
bot = Bot(token, botPrefix, initChannels)
bot.run()
# bot.run() is blocking and will stop execution of any below code here until stopped or closed.
